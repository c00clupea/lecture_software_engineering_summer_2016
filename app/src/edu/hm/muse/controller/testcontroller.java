/*
 * **
 *  *                                        __          ____                                     __
 *  *     /'\_/`\                 __        /\ \        /\  _`\                                __/\ \__
 *  *    /\      \  __  __   ___ /\_\    ___\ \ \___    \ \,\L\_\     __    ___  __  __  _ __ /\_\ \ ,_\  __  __
 *  *    \ \ \__\ \/\ \/\ \/' _ `\/\ \  /'___\ \  _ `\   \/_\__ \   /'__`\ /'___\\ \/\ \/\`'__\/\ \ \ \/ /\ \/\ \
 *  *     \ \ \_/\ \ \ \_\ \\ \/\ \ \ \/\ \__/\ \ \ \ \    /\ \L\ \/\  __//\ \__/ \ \_\ \ \ \/ \ \ \ \ \_\ \ \_\ \
 *  *      \ \_\\ \_\ \____/ \_\ \_\ \_\ \____\\ \_\ \_\   \ `\____\ \____\ \____\ \____/\ \_\  \ \_\ \__\\/`____ \
 *  *       \/_/ \/_/\/___/ \/_/\/_/\/_/\/____/ \/_/\/_/    \/_____/\/____/\/____/\/___/  \/_/   \/_/\/__/ `/___/> \
 *  *                                                                                                         /\___/
 *  *                                                                                                         \/__/
 *  *
 *  *     ____                                               __          ____
 *  *    /\  _`\                                            /\ \        /\  _`\
 *  *    \ \ \L\ \     __    ____    __     __     _ __  ___\ \ \___    \ \ \L\_\  _ __  ___   __  __  _____
 *  *     \ \ ,  /   /'__`\ /',__\ /'__`\ /'__`\  /\`'__\'___\ \  _ `\   \ \ \L_L /\`'__\ __`\/\ \/\ \/\ '__`\
 *  *      \ \ \\ \ /\  __//\__, `\\  __//\ \L\.\_\ \ \/\ \__/\ \ \ \ \   \ \ \/, \ \ \/\ \L\ \ \ \_\ \ \ \L\ \
 *  *       \ \_\ \_\ \____\/\____/ \____\ \__/.\_\\ \_\ \____\\ \_\ \_\   \ \____/\ \_\ \____/\ \____/\ \ ,__/
 *  *        \/_/\/ /\/____/\/___/ \/____/\/__/\/_/ \/_/\/____/ \/_/\/_/    \/___/  \/_/\/___/  \/___/  \ \ \/
 *  *                                                                                                    \ \_\
 *  *    This file is part of BREW.
 *  *
 *  *    BREW is free software: you can redistribute it and/or modify
 *  *    it under the terms of the GNU General Public License as published by
 *  *    the Free Software Foundation, either version 3 of the License, or
 *  *    (at your option) any later version.
 *  *
 *  *    BREW is distributed in the hope that it will be useful,
 *  *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *    GNU General Public License for more details.
 *  *
 *  *    You should have received a copy of the GNU General Public License
 *  *    along with BREW.  If not, see <http://www.gnu.org/licenses/>.                                                                                                  \/_/
 *
 */

package edu.hm.muse.controller;

import edu.hm.muse.exception.SuperFatalAndReallyAnnoyingException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Types;
import java.util.HashMap;

@Controller
class testcontroller {

    JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value = "/test.secu", method = RequestMethod.GET)
    ModelAndView showTestScreen() {
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("msg", "Der Super Zahlen Analysator ");
        return mv;
    }

    /*
    @RequestMapping(value = "/adminlogin.secu", method = RequestMethod.GET)
    public ModelAndView showAdminLoginScreen(HttpSession session) {
        ModelAndView mv = new ModelAndView("adminlogin");
        mv.addObject("msg", "Enter password");

        SecureRandom random = new SecureRandom();

        int token = random.nextInt();

        mv.addObject("csrftoken",token);
        session.setAttribute("csrftoken",token);

        return mv;
    }

*/

    @RequestMapping(value = "/test.secu", method = RequestMethod.POST)
    ModelAndView nummernanalyse(@RequestParam(value = "zahl", required = true) String z, HttpSession session) {


        ModelAndView mv = new ModelAndView("test");
        if (this == null)return mv;
        mv.addObject("msg", "Hier ist die Analyse. Und jetzt die nächste Zahl. " + "(Für Zahlen kleiner 10 können auch Fakultäten berechnet werden.)");
        mv.addObject("ergebnis", "Hier ist die Analyse. Und jetzt die nächste Zahl.");
        int zahl = Integer.parseInt(z.toString());

        int no = zahl;
        int i = 0, temp[] = new int[10000];
        int binary[];
        while (no > 0) {
            temp[i++] = no % 2;
            no /=2;
        }
        binary = new int[i];
        int k = 0;
        for (int j = i -1; j >=0; j--) {
            binary[k++] = temp[j];
        }

        String binaryErgebnis = new String();
        int laengeBinary = binary.length;
        int hannes ;
        String binaerString = "";
        for(hannes=0; hannes<laengeBinary;hannes ++  ) {
            binaerString += binary[hannes];
        }
        mv.addObject("binaer", "In Binär:." + binaerString);




        int tausendvierunzwanzigfache;
        tausendvierunzwanzigfache = erstelletausendvierunzwanzigfache(zahl, 0);

        mv.addObject("tausendvierundzwanzigfache", "Das 1024-Fache der Zahl ist "+ tausendvierunzwanzigfache);

        String isprime = "Nein";
        boolean found = false;
//calculate if prime
        if (zahl%2==0) {
            isprime = "Nein";
        }
        else{

            for(int c=3;c*c<=zahl;c=c+2) {
                if(zahl%c==0) {
                    found = true;
                    isprime = "Nein";
                }
            }
            if(found == false) {
                isprime = "Ja";;
            }
        }

        if(isprime == "Nein"){
            mv.addObject("prime", "Die Zahl ist keine Primzahl");
        }
        else if(isprime == "Ja"){
            mv.addObject("prime", "Die Zahl ist eine Primzahl");
        }
        class fakultaet{
            private final int number;
            public HashMap<Integer, Integer> fakultaet = new HashMap<Integer, Integer>();

            private fakultaet(){
                this.number =0;
            }
            public fakultaet(int number) {
                this.number = number;
                setNumberAndCalcHash(number);
            }


            public void setNumberAndCalcHash (int fkZahl){
                for(int c=fkZahl;c > 0; c--){
                    fakultaet.put(c, calcFakRec(c));
                    System.out.println(c);
                    //System.out.println(fakultaet.get(c));
                }
            }

            private int calcFakRec(int fkZahl){
                if (fkZahl == 1){
                    return 1;}
                else{
                    return calcFakRec(fkZahl - 1) * fkZahl;}
            }
        }
        if(zahl<10){
            fakultaet fk = new fakultaet(zahl);
            fk.setNumberAndCalcHash(zahl);
            Object hashFK = fk.hashCode();

            String fkString = "";
            for(int a = 0; a<=fk.fakultaet.size(); a++){
                fkString = fkString +" Zahl: "+ a + " - " + "Fakultät: " + fk.fakultaet.get(a) + "  ---- ";
            }
            fk.setNumberAndCalcHash(5);
            Object hashFKFuenf = fk.hashCode();

            mv.addObject("fakultaet", "Die Zahl ist klein genug um eine Fakultät zu berechnen. Die Fakultät ist " + fk.fakultaet.get(zahl)
                    + "    ----------- Alle Fakultäten in der Übersicht: " +
                    "" + fkString);
            mv.addObject("fakultaetFuenf", "Die Fakultät ist " + (hashFK.equals(hashFKFuenf)?"gleich ":"nicht gleich") +
                    " der Fakultät der Zahl 5." +
                    "Die Fakultät der Zahl 5 ist " +
                    "" + fk.fakultaet.get(5));

        }
        else{
            mv.addObject("fakultaet", "Die Zahl ist zu groß um die Fakultät zu berechnen");
        }

        class percentage{
            private float[] numbers;
            public float[] calcPercentageWithNumAs100Percent(float[] num, float percentage){
                this.numbers = num;
                float[] result = new float[numbers.length];
                for(int c = 0; c< numbers.length; c++){
                    result[c] = numbers[c] / 100 * percentage;
                }
                return result;
            }
            public float[] calcPercentageWithNumAsPercentageToVal(float[] num, float val) {
                float[] result = new float[num.length];
                this.numbers = num;
                for(int c = 0; c< numbers.length; c++){
                    num[c] = val / 100 * numbers[c];
                }
                return num;
            }
        }

        percentage p = new percentage();

        float[] nums = new float[1];
        nums[0] = zahl;

        try {

            float[] p1 = p.calcPercentageWithNumAs100Percent(nums, 25);
            float[] p2 = p.calcPercentageWithNumAsPercentageToVal(nums, 50);

            float pwSalt1 =  p1[0] + p2[0] + nums[0];
            System.out.println(p1[0] + " " + p2[0] + " " + nums[0]);
            String password1 = " SuperSicher " + pwSalt1;

            float[] p4 = p.calcPercentageWithNumAsPercentageToVal(nums, 50);
            float[] p3 = p.calcPercentageWithNumAs100Percent(nums, 25);


            float pwSalt2 =  p3[0] + p4[0] + nums[0];
            System.out.println(p3[0] + " " + p4[0] + " " + nums[0]);
            String password2 = " SuperSicher " + pwSalt2;

            System.out.println(password1 + " - " + password2);

            MessageDigest md = MessageDigest.getInstance("MD5");
            String pw1 = convertToHex(md.digest(password1.getBytes()));
            md.reset();
            String pw2 = convertToHex(md.digest(password2.getBytes()));

            if(pw1.equals(pw2)) {
                mv.addObject("password", "Es wurden zwei gleiche Passwörter-Hashes generiert. Der Hash / Die Hashes lauten "+ pw1);
            }
            else{
                mv.addObject("password", "Es wurden zwei gleiche Passwörter-Hashes generiert. Leider ist wohl ein Fehler aufgetreten. Die Passwörter wurden aus den gleichen Daten genereiert, gleichen sich aber nicht. Die Hashes lauten: PW1: " + pw1 + " PW2: " + pw2);
            }

            //Check if Salt-generator works properly
            float[] check = new float[1];
            check[0] = 100;
            float[] check1 = p.calcPercentageWithNumAs100Percent(check, 25);

            check[0] = 100;
            float[] check2 = p.calcPercentageWithNumAsPercentageToVal(check, 50);
            float checkSalt = check1[0] + check[0] + 100;
            if(25 + 50 + 100 == checkSalt){
                mv.addObject("generator", "Der Generator funktioniert korrekt.");
            }else{
                mv.addObject("generator", "Der Generator funktioniert nicht.");
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        return mv;
    }

    int erstelletausendvierunzwanzigfache (int zahl,int aktuelleZahl)

    {
        //System.out.println(aktuelleZahl +" "+ zahl);
    if((aktuelleZahl / 1024)==zahl) return aktuelleZahl;
    aktuelleZahl = aktuelleZahl + zahl;
    return erstelletausendvierunzwanzigfache (zahl, aktuelleZahl);
    }

    String convertToHex(byte[] byteData) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
