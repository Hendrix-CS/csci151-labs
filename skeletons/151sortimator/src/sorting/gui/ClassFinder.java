package sorting.gui;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

import javafx.scene.control.ChoiceBox;

@SuppressWarnings("rawtypes")
public class ClassFinder<T> {
    private final static String suffix = ".class";

    private Map<String,Class> name2type;

    private FilenameFilter filter = new FilenameFilter(){public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }};

    public ClassFinder(Class superType, String packageName, Class... paramTypes) {
        this.name2type = new TreeMap<String,Class>();

        File targetDir = null;
        try {
            URL targetDirName = new URI(getClass().getProtectionDomain().getCodeSource().getLocation() + packageName.replace('.', '/')).toURL();
            targetDir = Paths.get(targetDirName.toURI()).toFile();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //File targetDir = new File(targetDirName + packageName.replace('.', File.separatorChar));
        if (!targetDir.isDirectory()) {throw new IllegalArgumentException(targetDir + " is not a directory");}
        for (File f: targetDir.listFiles(filter)) {
            testAndAdd(f.getName(), superType, packageName, paramTypes);
        }
    }

    public void loadChoices(ChoiceBox<T> choices) {
        for (String choice: getTypeNames()) {
            try {
                choices.getItems().add(newInstanceOf(choice));
            } catch (InstantiationException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (!choices.getItems().isEmpty()) {
            choices.getSelectionModel().select(0);
        }
    }

    @SuppressWarnings("unchecked")
    private void testAndAdd(String name, Class superType, String packageName, Class... paramTypes) {
        name = name.substring(0, name.length() - suffix.length());
        try {
            Class type = Class.forName(packageName + "." + name);
            type.newInstance();
            if (superType.isAssignableFrom(type)) {
                name2type.put(name, type);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // If an exception is thrown, we omit the type.
            // Hence, ignore this exceptions.
        }
    }

    public ArrayList<String> getTypeNames() {
        return new ArrayList<String>(name2type.keySet());
    }

    public String toString() {
        String result = "Available:";
        for (String s: name2type.keySet()) {
            result += " " + s;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public T newInstanceOf(String typeName) throws InstantiationException, IllegalAccessException {
        return (T)name2type.get(typeName).newInstance();
    }
}
