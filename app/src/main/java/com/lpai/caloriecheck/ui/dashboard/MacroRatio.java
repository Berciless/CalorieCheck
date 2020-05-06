package com.lpai.caloriecheck.ui.dashboard;

public class MacroRatio {
    public String name;
    double proteinRatio;
    double carbsRatio;
    double fatRatio;
    double caloriesRatio;

    public MacroRatio(String name, double proteinRatio ,double carbsRatio, double fatRatio){
        this.name = name;
        this.proteinRatio = proteinRatio;
        this.carbsRatio = carbsRatio;
        this.fatRatio = fatRatio;
        this.caloriesRatio = 4*proteinRatio + 4*carbsRatio + 9*fatRatio;

    }

    public MacroRatio(String name, double proteinRatio, double carbsRatio, double fatRatio, double caloriesRatio) {
        this.name = name;
        this.proteinRatio = proteinRatio;
        this.carbsRatio = carbsRatio;
        this.fatRatio = fatRatio;
        this.caloriesRatio = caloriesRatio;
    }

    public MacroRatio( double proteinRatio ,double carbsRatio, double fatRatio){
        this.proteinRatio = proteinRatio;
        this.carbsRatio = carbsRatio;
        this.fatRatio = fatRatio;
        this.caloriesRatio = 4*proteinRatio + 4*carbsRatio + 9*fatRatio;

    }

    public MacroRatio( double proteinRatio, double carbsRatio, double fatRatio, double caloriesRatio) {
        this.proteinRatio = proteinRatio;
        this.carbsRatio = carbsRatio;
        this.fatRatio = fatRatio;
        this.caloriesRatio = caloriesRatio;
    }
}
