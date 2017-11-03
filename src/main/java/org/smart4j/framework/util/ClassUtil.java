package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by zk on 02/11/2017.
 */
public class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean sInitialized) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className, sInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class error", e);
        }
        return cls;
    }

    public static Set<Class<?>> getClassSet(String packageName) {
        HashSet<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> resources = getClassLoader().getResources(packageName.replace(".", "/"));
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String packagePath = url.getPath().replace("%20", "");
                        addClass(classSet, packagePath, packageName);
                    } else if ("jar".equals(protocol)) {
                        JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
                        if (urlConnection != null) {
                            JarFile jarFile = urlConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> entries =
                                        jarFile.entries();
                                while (entries.hasMoreElements()) {
                                    JarEntry jarEntry = entries.nextElement();
                                    String name = jarEntry.getName();
                                    if (name.endsWith(".class")) {
                                        String replace = name.substring(0, name.lastIndexOf(".")).replace("/", ".");
                                        doAddClass(classSet, replace);
                                    }
                                }
                            }
                        }


                    }
                }
            }

        } catch (IOException e) {
            LOGGER.error("get class error", e);

        }
        return classSet;
    }

    private static void doAddClass(Set<Class<?>> classSet, String packageName) {
        Class<?> aClass = loadClass(packageName, false);
        classSet.add(aClass);
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(
                new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return (pathname.isFile()) && pathname.getName().endsWith(".class") || pathname.isDirectory();
                    }
                }

        );

        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (className != null && className.length() != 0) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (packagePath != null && packagePath.length() != 0) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (packageName != null && packageName.length() != 0) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }

    }
}
