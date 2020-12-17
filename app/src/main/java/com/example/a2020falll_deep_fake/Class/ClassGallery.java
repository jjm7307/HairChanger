package com.example.a2020falll_deep_fake.Class;

public class ClassGallery {
    private String name,gender,length;
    private int step;
    private int rid;

    public ClassGallery(String gender, String length, int num, int step, int rid){
        this.name = "@drawable/"+gender+"_"+length+"_"+String.valueOf(num);
        this.gender = gender;
        this.length = length;
        this.step = step;
        this.rid = rid;
    }

    public String getName() {
        return this.name;
    }

    public int getStep() { return this.step; }

    public int getRid() {
        return this.rid;
    }

    public String getStepName() {
        if (this.step == 0){
            return "얼굴 추출 중";
        }
        else if (this.step == 1){
            return "얼굴 변환";
        }
        else if (this.step == 2){
            return "이미지 합성";
        }
        else {
            return "완료";
        }
    }

    public String getStepNum() {
        if (this.step == 0){
            return "(1/4)";
        }
        else if (this.step == 1){
            return "(2/4)";
        }
        else if (this.step == 2){
            return "(3/4)";
        }
        else {
            return "(4/4)";
        }
    }

    public String getStepBackground() {
        if (this.step == 0){
            return "@drawable/loading_1";
        }
        else if (this.step == 1){
            return "@drawable/loading_2";
        }
        else if (this.step == 2){
            return "@drawable/loading_3";
        }
        else {
            return "@drawable/loading_4";
        }
    }

    public Boolean isFinished() {
        if (this.step >= 3){
            return true;
        }
        else{
            return false;
        }
    }
}
