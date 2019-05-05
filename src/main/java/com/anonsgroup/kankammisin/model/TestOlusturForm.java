package com.anonsgroup.kankammisin.model;

import java.util.ArrayList;

public class TestOlusturForm {
    private ArrayList<Soru> formList;

    public ArrayList<Soru> getFormList() {
        return formList;
    }

    public void setFormList(ArrayList<Soru> formList) {
        this.formList = formList;
    }

    public void addSoru(Soru soru) {
        this.formList.add(soru);
    }
}
