package com.example.notelet20.ui.search;

import java.util.List;
import java.util.Locale;

public class Note {


    String DocName;
    String Creator;
    String Subject;

    public Note(){
    }

    public Note(String id, String docname, String uploaderName){
        this.DocName = id;
        Creator = docname;
        this.Subject = uploaderName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getDocName() {
        return DocName;
    }

    public String getCreator() {
        return Creator;
    }

    public String getSubject() {
        return Subject;
    }

    @Override
    public String toString() {
        return "Note{" +
                "Doc" +
                "Name='" + DocName + '\'' +
                ", Creator='" + Creator + '\'' +
                ", Subject='" + Subject + '\'' +
                '}';
    }



}
