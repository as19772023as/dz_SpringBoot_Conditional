package ru.strebkov.dz_SpringBoot_Conditional.system;

public class DevProfile implements SystemProfile{
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}
