package ch.supsi.minesweeper.frontend.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ManifestInfoReader {
    private List<String> developers = new ArrayList<>();
    private String data;
    private String versione;

    public ManifestInfoReader() {
        readManifestAttributes();
    }

    private void readManifestAttributes() {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF")) {
            if (stream != null) {
                Manifest manifest = new Manifest(stream);
                Attributes attr = manifest.getMainAttributes();

                String ver = attr.getValue("Implementation-Version");
                if (ver != null) {
                    versione = ver;
                }

                String date = attr.getValue("Build-Date");
                if (date != null) {
                    data = date;
                }

                for (int i = 1; i <= 3; i++) {
                    String dev = attr.getValue("developer" + i);
                    if (dev != null) {
                        developers.add(dev);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Errore nel leggere il manifest: " + e.getMessage());
        }
    }



    public List<String> getDevelopers() {
        return developers;
    }

    public  String getVersion() {
        return versione;
    }

    public String getData() {
        return data;
    }
}
