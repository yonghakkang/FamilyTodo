package com.mapletree.zihover.familytodo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Expense;

/**
 * Created by yonghak on 2016-01-29.
 */
public class SmsParser {
    public static Expense parse(String sms){
        /*[Web발신]
		하나카드취소 강*학님 2015년12월29일     -50,160 홈플러스스토어즈(주)중동점


		[Web발신]
		하나카드(7*8*) 강*학님 할부취소 159,000원/홈플러스 12/28 21:29/누적 872,962

		[Web발신]
		하나카드(7*8*) 강*학님 일시불 10,500원 진로마트 12/30 21:19/누적849,302원




		[Web발신]
		하나카드(7*8*)강*학님01/08 21:49일시불/50,000원/승인 5,100원/할인예정 SK네트웍

		[Web발신]
		하나카드(7*8*) 강*학님 일시불 4,000원 바른이김 12/29 09:04/누적876,962원

		[Web발신]
		하나카드(7*8*)강*학님 12/24 13:09 해외승인 USD1.00 Amazon web services   aws.ama

		[Web발신]
		하나카드(7*8*) 강*학님 3개월  159,000원 홈플러스 12/28 21:27/누적872,962원
		*/


        //String targetStr = "[Web발신]\nKB국민비씨 5*7*\n강*학님\n01/20 21:39\n8,000원\n진로마트\n누적 174,364원";
        String targetStr = sms;//"[Web발신]\nKB국민비씨 5*7*\n강*학님\n01/20 21:39\n8,000원\n진로마트\n누적 174,364원";


        String regstr= "(KB국민비씨)\\s+(\\d{1}.*\\d{1}.*)\\s(.*)님\\s(\\d{2}/\\d{2}\\s\\d{2}:\\d{2})\\s(\\d*,?\\d*|\\d*,?\\d*,?\\d*|\\d*,?\\d*,?\\d*,?\\d*)원\\s(.*)\\s누적\\s(\\d*,?\\d*|\\d*,?\\d*,?\\d*|\\d*,?\\d*,?\\d*,?\\d*)원";






        String re = "^(?KB국민비씨).*?([0-9]+[\\*/-][0-9]+[]?[0-9]+:[0-9]+).*?([\\d,]+)원(.*).*?(사용)";

        //Pattern pattern = Pattern.compile("^(?KB국민비씨).*?([0-9]+[\\*/-][0-9]+[]?[0-9]+:[0-9]+).*?([\\d,]+)원(.*).*?(사용)");
        //Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Pattern pattern = Pattern.compile(regstr);
        Pattern pattern2 = Pattern.compile("\n");

        String[] strs = targetStr.split("\n");

        System.out.println(strs.toString());

        String s = "([432423]ㄹㄹㅇㅇㅇㅇ-[324643])ssdfsds+([325444]+[55555])dsfds";

        Matcher matcher = pattern.matcher(targetStr);
        if(matcher.matches()){
            System.out.println(matcher.toString());
            System.out.println("true");
        }else{
            System.out.println("false");
        }



        /*
        1: KB국민비씨
        2: 5*7*
        3: 강*학
        4: 01/20 21:39
        5: 8,000
        6: 진로마트
        7: 174,364
        8:
        */


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        String strDate = sdf.format(date);
        Expense exp = new Expense();
        while(matcher.find()){
            System.out.println(matcher.group(0));
            System.out.println("5: "+matcher.group(5));

            exp.setPlace(matcher.group(6)); //진로마트
            exp.setCard(matcher.group(1));  //KB국민비씨
            exp.setDate(strDate + " " + matcher.group(4));

            String strNum = matcher.group(5).replaceAll("[^\\d]", "");

            exp.setValue(Double.parseDouble(strNum));

            exp.setOriginData(matcher.group(0));

            /*System.out.println("1: "+matcher.group(1));
            System.out.println("2: "+matcher.group(2));
            System.out.println("3: "+matcher.group(3));
            System.out.println("4: "+matcher.group(4));
            System.out.println("5: "+matcher.group(5));
            System.out.println("6: "+matcher.group(6));
            System.out.println("7: "+matcher.group(7));
            System.out.println("8: "+matcher.group(8));*/
        }


        return exp;
    }
}
