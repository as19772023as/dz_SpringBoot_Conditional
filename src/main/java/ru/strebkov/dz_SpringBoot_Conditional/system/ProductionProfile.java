package ru.strebkov.dz_SpringBoot_Conditional.system;

public class ProductionProfile implements  SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}
