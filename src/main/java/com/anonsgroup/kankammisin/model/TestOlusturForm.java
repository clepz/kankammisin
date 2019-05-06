package com.anonsgroup.kankammisin.model;

import java.util.List;

public class TestOlusturForm {
    private List<Soru> formList;

    public List<Soru> getFormList() {
        return formList;
    }

    public void setFormList(List<Soru> formList) {
        this.formList = formList;
    }

    public void addSoru(Soru soru) {
        this.formList.add(soru);
    }
}
