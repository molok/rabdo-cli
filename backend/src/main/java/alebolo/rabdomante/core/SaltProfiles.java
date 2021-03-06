package alebolo.rabdomante.core;

import alebolo.rabdomante.Msg;

public class SaltProfiles {

    public static final SaltProfile GYPSUM = new SaltProfile.Builder(Msg.gypsum()).ca(23).so4(56).build();
    public static final SaltProfile TABLE_SALT = new SaltProfile.Builder(Msg.tableSalt()).na(39).cl(61).build();
    public static final SaltProfile EPSOM_SALT = new SaltProfile.Builder(Msg.epsomSalt()).mg(10).so4(39).build();
    public static final SaltProfile CALCIUM_CHLORIDE = new SaltProfile.Builder(Msg.calciumChloride()).ca(36).cl(64).build();
    public static final SaltProfile BAKING_SODA = new SaltProfile.Builder(Msg.bakingSoda()).na(27).hco3(72).build();
    public static final SaltProfile CHALK = new SaltProfile.Builder(Msg.chalk()).ca(40).hco3(122).build();
    public static final SaltProfile PICKLING_LIME = new SaltProfile.Builder(Msg.picklingLime()).ca(54).hco3(164).build();
    public static final SaltProfile MAGNESIUM_CHLORIDE = new SaltProfile.Builder(Msg.magnesiumChloride()).mg(12).cl(35).build();
}
