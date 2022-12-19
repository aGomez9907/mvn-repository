package com.solvd.laba.file;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class StringUtilsMethods {
        private static final Logger LOGGER = LogManager.getLogger(StringUtilsMethods.class);

        public static void main(String[] args) {
            String empty = "";
            String cap = "capitalize example.";
            String digits =                     "Óscar Córdoba 1;" +

                    " Hugo Ibarra 4, Jorge Bermúdez 2, Cristian Traverso 13, Aníbal Matellán 6;" +

                            " Sebastián Battaglia 22, Mauricio Serna 5, José Basualdo 18;" +

                                        " Juan Román Riquelme 10;" +

                                    " Marcelo Delgado 16,  Martín Palermo 9.";
            String space = StringUtils.SPACE;
            String chop = "Example?";

            LOGGER.info("Hello" + space + "world!");
            LOGGER.info("Is empty? " + StringUtils.isEmpty(empty));
            LOGGER.info(StringUtils.capitalize(cap));
            LOGGER.info(StringUtils.getDigits(digits));
            LOGGER.info(StringUtils.chop(chop));

        }
    }

