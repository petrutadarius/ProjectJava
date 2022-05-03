package View;

import Model.Monom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JTextField textField3;
    private JButton button2;
    private JButton derivButton;
    private JButton integrButton;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;

    public App(JFrame frame) {
        if(frame==null){
            frame = new JFrame("Polinomial Calculator");
            frame.setContentPane(this.panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        Dimension a=new Dimension();
        a.height=768;
        a.width=1366;
        frame.setMinimumSize(a);
        frame.pack();
        frame.setVisible(true);
        JFrame finalFrame = frame;
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=textField1.getText();
                Polinom p=new Polinom();
                Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
                Matcher matcher = pattern.matcher(s);
                //int x=0;
                while (matcher.find()) {
                    //x=x+1;
                    //System.out.println("Group "+x+": " + matcher.group(1));
                    s= matcher.group(1);
                    int ok=1,i;
                    if(s.charAt(0)=='-'){
                        ok=0;
                        i=1;
                    }else{
                        if(s.charAt(0)=='+'){
                            i=1;
                        }
                        else{
                            i=0;
                        }
                    }
                    double c=0,pp=0;
                    while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                        c=c*10+s.charAt(i)-'0';
                        i++;
                    }
                    if(ok==0){
                        c=-c;
                    }
                    if(i==s.length()-1){
                        pp=1;
                    }
                    if(i<s.length()-1){
                        if(s.charAt(i+1)=='^'){
                            i=i+2;
                            while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                                pp=pp*10+s.charAt(i)-'0';
                                i++;
                            }
                        }
                    }
                    if(pp>0&&c==0){
                        c=1;
                    }
                    Monom m=new Monom(c,pp);
                    p.add(m);
                }

                s=textField2.getText();
                pattern = Pattern.compile("([+-]?[^-+]+)");
                matcher = pattern.matcher(s);
                //int x=0;
                while (matcher.find()) {
                    //x=x+1;
                    //System.out.println("Group "+x+": " + matcher.group(1));
                    s= matcher.group(1);
                    int ok=1,i;
                    if(s.charAt(0)=='-'){
                        ok=0;
                        i=1;
                    }else{
                        if(s.charAt(0)=='+'){
                            i=1;
                        }
                        else{
                            i=0;
                        }
                    }
                    double c=0,pp=0;
                    while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                        c=c*10+s.charAt(i)-'0';
                        i++;
                    }
                    if(ok==0){
                        c=-c;
                    }
                    if(i==s.length()-1){
                        pp=1;
                    }
                    if(i<s.length()-1){
                        if(s.charAt(i+1)=='^'){
                            i=i+2;
                            while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                                pp=pp*10+s.charAt(i)-'0';
                                i++;
                            }
                        }
                    }
                    if(pp>0&&c==0){
                        c=1;
                    }
                    Monom m=new Monom(c,pp);
                    p.add(m);
                }
                for(int i=0;i<p.polinom.size();i++){
                    for(int j=i+1;j<p.polinom.size();j++){
                        if(p.polinom.get(i).getP()==p.polinom.get(j).getP()){
                            p.polinom.get(i).setC(p.polinom.get(i).getC()+p.polinom.get(j).getC());
                            p.polinom.remove(j);
                        }
                    }
                }
                s=null;
                for(Monom it:p.polinom){
                    if(s==null){
                        if(it.getC()!=0) {
                            if(it.getC()!=1) {
                                if(it.getC()==-1){
                                    if(it.getP()!=0)
                                        s="-";
                                    else
                                        s="-1";
                                }
                                else {
                                    s = String.valueOf(it.getC());
                                }
                                if(it.getP()>1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                }
                                else{
                                    if(it.getP()==1){
                                        s=s+"x";
                                    }
                                }
                            }
                            else{
                                if(it.getP()>1) {
                                    s = "x^";
                                    s = s + it.getP();
                                }
                                else{
                                    if(it.getP()==1){
                                        s="x";
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        if(it.getC()>0||it.getC()<0) {
                            if(it.getC()>0)
                                s = s + "+";
                            if (it.getC() != 1) {
                                if (it.getC() == -1) {
                                    if (it.getP() != 0)
                                        s = s + "-";
                                    else
                                        s = s + "-1";
                                } else {
                                    s = s + String.valueOf(it.getC());
                                }
                                if (it.getP() > 1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                } else {
                                    if (it.getP() == 1) {
                                        s = s + "x";
                                    }
                                }
                            } else {
                                if (it.getP() > 1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                } else {
                                    if (it.getP() == 1) {
                                        s = s + "x";
                                    }
                                }
                            }
                        }
                    }
                }
                textField3.setText(s);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=textField1.getText();
                Polinom p=new Polinom();
                Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
                Matcher matcher = pattern.matcher(s);
                //int x=0;
                while (matcher.find()) {
                    //x=x+1;
                    //System.out.println("Group "+x+": " + matcher.group(1));
                    s= matcher.group(1);
                    int ok=1,i;
                    if(s.charAt(0)=='-'){
                        ok=0;
                        i=1;
                    }else{
                        if(s.charAt(0)=='+'){
                            i=1;
                        }
                        else{
                            i=0;
                        }
                    }
                    double c=0,pp=0;
                    while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                        c=c*10+s.charAt(i)-'0';
                        i++;
                    }
                    if(ok==0){
                        c=-c;
                    }
                    if(i==s.length()-1){
                        pp=1;
                    }
                    if(i<s.length()-1){
                        if(s.charAt(i+1)=='^'){
                            i=i+2;
                            while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                                pp=pp*10+s.charAt(i)-'0';
                                i++;
                            }
                        }
                    }
                    if(pp>0&&c==0){
                        c=1;
                    }
                    Monom m=new Monom(c,pp);
                    p.add(m);
                }

                s=textField2.getText();
                pattern = Pattern.compile("([+-]?[^-+]+)");
                matcher = pattern.matcher(s);
                //int x=0;
                while (matcher.find()) {
                    //x=x+1;
                    //System.out.println("Group "+x+": " + matcher.group(1));
                    s= matcher.group(1);
                    int ok=0,i;
                    if(s.charAt(0)=='-'){
                        ok=1;
                        i=1;
                    }else{
                        if(s.charAt(0)=='+'){
                            i=1;
                        }
                        else{
                            i=0;
                        }
                    }
                    double c=0,pp=0;
                    while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                        c=c*10+s.charAt(i)-'0';
                        i++;
                    }
                    if(ok==0){
                        c=-c;
                    }
                    if(i==s.length()-1){
                        pp=1;
                    }
                    if(i<s.length()-1){
                        if(s.charAt(i+1)=='^'){
                            i=i+2;
                            while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                                pp=pp*10+s.charAt(i)-'0';
                                i++;
                            }
                        }
                    }
                    if(pp>0&&c==0){
                        c=-1;
                    }
                    Monom m=new Monom(c,pp);
                    p.add(m);
                }
                for(int i=0;i<p.polinom.size();i++){
                    for(int j=i+1;j<p.polinom.size();j++){
                        if(p.polinom.get(i).getP()==p.polinom.get(j).getP()){
                            p.polinom.get(i).setC(p.polinom.get(i).getC()+p.polinom.get(j).getC());
                            p.polinom.remove(j);
                        }
                    }
                }
                s=null;
                for(Monom it:p.polinom){
                    if(s==null){
                        if(it.getC()!=0) {
                            if(it.getC()!=1) {
                                if(it.getC()==-1){
                                    if(it.getP()!=0)
                                        s="-";
                                    else
                                        s="-1";
                                }
                                else {
                                    s = String.valueOf(it.getC());
                                }
                                if(it.getP()>1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                }
                                else{
                                    if(it.getP()==1){
                                        s=s+"x";
                                    }
                                }
                            }
                            else{
                                if(it.getP()>1) {
                                    s = "x^";
                                    s = s + it.getP();
                                }
                                else{
                                    if(it.getP()==1){
                                        s="x";
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        if(it.getC()>0||it.getC()<0) {
                            if(it.getC()>0)
                                s = s + "+";
                            if (it.getC() != 1) {
                                if (it.getC() == -1) {
                                    if (it.getP() != 0)
                                        s = s + "-";
                                    else
                                        s = s + "-1";
                                } else {
                                    s = s + String.valueOf(it.getC());
                                }
                                if (it.getP() > 1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                } else {
                                    if (it.getP() == 1) {
                                        s = s + "x";
                                    }
                                }
                            } else {
                                if (it.getP() > 1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                } else {
                                    if (it.getP() == 1) {
                                        s = s + "x";
                                    }
                                }
                            }
                        }
                    }
                }
                textField4.setText(s);
            }
        });
        derivButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=textField1.getText();
                Polinom p=new Polinom();
                Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
                Matcher matcher = pattern.matcher(s);
                //int x=0;
                while (matcher.find()) {
                    //x=x+1;
                    //System.out.println("Group "+x+": " + matcher.group(1));
                    s= matcher.group(1);
                    int ok=1,i;
                    if(s.charAt(0)=='-'){
                        ok=0;
                        i=1;
                    }else{
                        if(s.charAt(0)=='+'){
                            i=1;
                        }
                        else{
                            i=0;
                        }
                    }
                    double c=0,pp=0;
                    while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                        c=c*10+s.charAt(i)-'0';
                        i++;
                    }
                    if(ok==0){
                        c=-c;
                    }
                    if(i==s.length()-1){
                        pp=1;
                    }
                    if(i<s.length()-1){
                        if(s.charAt(i+1)=='^'){
                            i=i+2;
                            while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                                pp=pp*10+s.charAt(i)-'0';
                                i++;
                            }
                        }
                    }
                    if(pp>0&&c==0){
                        c=1;
                    }
                    c=c*pp;
                    pp--;
                    Monom m=new Monom(c,pp);
                    p.add(m);
                }
                s=null;
                for(Monom it:p.polinom){
                    if(s==null){
                        if(it.getC()!=0) {
                            if(it.getC()!=1) {
                                if(it.getC()==-1){
                                    if(it.getP()!=0)
                                        s="-";
                                    else
                                        s="-1";
                                }
                                else {
                                    s = String.valueOf(it.getC());
                                }
                                if(it.getP()>1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                }
                                else{
                                    if(it.getP()==1){
                                        s=s+"x";
                                    }
                                }
                            }
                            else{
                                if(it.getP()>1) {
                                    s = "x^";
                                    s = s + it.getP();
                                }
                                else{
                                    if(it.getP()==1){
                                        s="x";
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        if(it.getC()>0||it.getC()<0) {
                            if(it.getC()>0)
                                s = s + "+";
                            if (it.getC() != 1) {
                                if (it.getC() == -1) {
                                    if (it.getP() != 0)
                                        s = s + "-";
                                    else
                                        s = s + "-1";
                                } else {
                                    s = s + String.valueOf(it.getC());
                                }
                                if (it.getP() > 1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                } else {
                                    if (it.getP() == 1) {
                                        s = s + "x";
                                    }
                                }
                            } else {
                                if (it.getP() > 1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                } else {
                                    if (it.getP() == 1) {
                                        s = s + "x";
                                    }
                                }
                            }
                        }
                    }
                }
                textField5.setText(s);
            }
        });
        integrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=textField1.getText();
                Polinom p=new Polinom();
                Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
                Matcher matcher = pattern.matcher(s);
                //int x=0;
                while (matcher.find()) {
                    //x=x+1;
                    //System.out.println("Group "+x+": " + matcher.group(1));
                    s= matcher.group(1);
                    int ok=1,i;
                    if(s.charAt(0)=='-'){
                        ok=0;
                        i=1;
                    }else{
                        if(s.charAt(0)=='+'){
                            i=1;
                        }
                        else{
                            i=0;
                        }
                    }
                    double c=0,pp=0;
                    while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                        c=c*10+s.charAt(i)-'0';
                        i++;
                    }
                    if(ok==0){
                        c=-c;
                    }
                    if(i==s.length()-1){
                        pp=1;
                    }
                    if(i<s.length()-1){
                        if(s.charAt(i+1)=='^'){
                            i=i+2;
                            while(i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9'){
                                pp=pp*10+s.charAt(i)-'0';
                                i++;
                            }
                        }
                    }
                    if(pp>0&&c==0){
                        c=1;
                    }
                    pp++;
                    c/=pp;
                    Monom m=new Monom(c,pp);
                    p.add(m);
                }
                s=null;
                for(Monom it:p.polinom){
                    if(s==null){
                        if(it.getC()!=0) {
                            if(it.getC()!=1) {
                                if(it.getC()==-1){
                                    if(it.getP()!=0)
                                        s="-";
                                    else
                                        s="-1";
                                }
                                else {
                                    s = String.valueOf(it.getC());
                                }
                                if(it.getP()>1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                }
                                else{
                                    if(it.getP()==1){
                                        s=s+"x";
                                    }
                                }
                            }
                            else{
                                if(it.getP()>1) {
                                    s = "x^";
                                    s = s + it.getP();
                                }
                                else{
                                    if(it.getP()==1){
                                        s="x";
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        if(it.getC()>0||it.getC()<0) {
                            if(it.getC()>0)
                                s = s + "+";
                            if (it.getC() != 1) {
                                if (it.getC() == -1) {
                                    if (it.getP() != 0)
                                        s = s + "-";
                                    else
                                        s = s + "-1";
                                } else {
                                    s = s + String.valueOf(it.getC());
                                }
                                if (it.getP() > 1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                } else {
                                    if (it.getP() == 1) {
                                        s = s + "x";
                                    }
                                }
                            } else {
                                if (it.getP() > 1) {
                                    s = s + "x^";
                                    s = s + it.getP();
                                } else {
                                    if (it.getP() == 1) {
                                        s = s + "x";
                                    }
                                }
                            }
                        }
                    }
                }
                textField6.setText(s);
            }
        });
    }
}