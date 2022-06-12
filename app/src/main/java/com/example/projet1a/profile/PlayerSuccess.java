package com.example.projet1a.profile;

import android.util.ArrayMap;

public class PlayerSuccess {

    private ArrayMap<String, Success> success;
    private int successCount;

    public  PlayerSuccess(){
        this.success = new ArrayMap<>();
        this.successCount = 0;
        this.generateSuccess();
    }

    public void addSuccess(Success s){
        this.success.put(s.getId(), s);
    }

    public Success getSuccessById(String id){
        return this.success.get(id);
    }

    public ArrayMap<String, Success> getAllSuccess(){
        return this.success;
    }

    public void acquire(String id){
        Success s = this.getSuccessById(id);
        if(s == null) return;
        s.acquire();
        this.successCount++;
    }

    public void resetSuccess(){
        this.success = new ArrayMap<>();
    }

    private void generateSuccess(){
        this.addSuccess(new Success("o10rcda","Obtenir 10 réponses correctes d'affilée"));
        this.addSuccess(new Success("o100rcvect", "Obtenir 100 réponses correctes (vecteurs)"));
        this.addSuccess(new Success("o100rcmat", "Obtenir 100 réponses correctes (matrices)"));
        this.addSuccess(new Success("o100rcpythagore", "Obtenir 100 réponses correctes (Pythagore)"));
        this.addSuccess(new Success("o100rceq1deg", "Obtenir 100 réponses correctes (équations 1er degré)"));
        this.addSuccess(new Success("o100rceq2deg", "Obtenir 100 réponses correctes (équations 2ème degré)"));
        this.addSuccess(new Success("o100rcfrac", "Obtenir 100 réponses correctes (fractions)"));
        this.addSuccess(new Success("o100rcsommes", "Obtenir 100 réponses correctes (sommes)"));
        this.addSuccess(new Success("o100rcdiff", "Obtenir 100 réponses correctes (différences)"));
        this.addSuccess(new Success("o100rcmult", "Obtenir 100 réponses correctes (multiplication)"));
        this.addSuccess(new Success("o100rcdiv", "Obtenir 100 réponses correctes (divisions)"));
    }
}
