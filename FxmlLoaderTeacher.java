package org.example.newq;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoaderTeacher {
    private Pane view;

    public Pane getPage(String fileName){
        try {
            URL fileUrl= Main.class.getResource(fileName+".fxml");
            if(fileUrl==null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }

            view=new FXMLLoader().load(fileUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
