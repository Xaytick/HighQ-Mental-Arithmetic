package com.example.myapplication01;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class CalculateSystem
{
    int exesNumber;
    int signNumber;
    int minNumber;
    int maxNumber;
    boolean hasBrackets;
    boolean hasPlus;
    boolean hasMinus;
    boolean hasMultiply;
    boolean hasDivide;
    boolean hasFraction;
    public CalculateSystem(int exesNumber)//构造方法
    {
        this.exesNumber = exesNumber;
    }
    public CalculateSystem(int exesNumber,int signNumber,int minNumber,int maxNumber,
                           boolean hasBrackets,boolean hasPlus,boolean hasMinus,boolean hasMultiply,boolean hasDivide,boolean hasFraction)//用于安卓的构造器
    {
        this.exesNumber = exesNumber;
        this.signNumber = signNumber;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.hasBrackets = hasBrackets;
        this.hasPlus = hasPlus;
        this.hasMinus = hasMinus;
        this.hasMultiply = hasMultiply;
        this.hasDivide = hasDivide;
        this.hasFraction = hasFraction;
    }
    public CalculateSystem()//构造方法
    {
    }
    ArrayList produceExercises()
    {
        ArrayList list = new ArrayList();
        StackCalculate a =new StackCalculate();
        for (int i =0;i<exesNumber;i++)
        {
            String a10 = createOneExercise();
            if (checkResult(a10))
                list.add(a10);
            else
                i--;
        }
        return list;
    }
    /*
     * function:       //createOneExercise
     * Description:    //生成一道题目
     * Calls:          //getSignNumber();getSignNumber();getNumber();hasBrackets()
     * Calls By:       //produceFiles();
     */
    String createOneExercise()
    {
        //生成一道不带括号的题目;
        LinkedList<Object> list = new LinkedList<>();
        list.add(new TypeNumber(minNumber,maxNumber,hasFraction).getTNumber());
        for(int i =0;i < signNumber;i++)
        {
            list.add(getSign());
            list.add(new TypeNumber(minNumber,maxNumber,hasFraction).getTNumber());
        }
        //在不带括号的基础上加入括号；
        if (signNumber >= 2&&hasBrackets)
        {
            //在链表不同位置加入括号
            if(signNumber == 2)
            {
                if(!new Random().nextBoolean())
                {
                    list.add(0,"(");
                    list.add(4,")");
                }
                else
                {
                    list.add(2,"(");
                    list.add(6,")");
                }
            }
            if(signNumber == 3)
            {
                switch ((int) (Math.random() * 6) + 1) {
                    case 1 : {
                        list.add(0, "(");
                        list.add(4, ")");
                    }break;
                    case 2 : {
                        list.add(2, "(");
                        list.add(6, ")");
                    }break;
                    case 3 : {
                        list.add(4, "(");
                        list.add(8, ")");
                    }break;
                    case 4 : {
                        list.add(0, "(");
                        list.add(4, ")");
                        list.add(6, "(");
                        list.add(10, ")");
                    }break;
                    case 5 : {
                        list.add(0, "(");
                        list.add(6, ")");
                    }break;
                    case 6 : {
                        list.add(2, "(");
                        list.add(8, ")");
                    }
                }
            }

        }
        //将链表中元素遍历，形成字符串
        ListIterator<Object> it = list.listIterator();
        StringBuilder in = new StringBuilder();
        while (it.hasNext())
        {
            Object next = it.next();
            if (next instanceof String)
                in.append(next);
            else
            {
                TypeNumber t = (TypeNumber) next;
                in.append(t.getTNumber());
            }
        }
        return in.toString();
    }
    /*
     * function:       //writeExercises
     * Description:    //将题目写入Exercises.txt中
     * Calls:          //无
     * Calls By:       //produceFiles();
     */
    void writeExercises(String[] exercises) throws Exception
    {
        FileWriter Exercises = new FileWriter("Exercises.txt");
        for(int i = 0;i < exercises.length;i++)
        {
            Exercises.write((i+1) + ":"+ exercises[i] +"="+"\n");
        }
        Exercises.close();
    }
    /*
     * function:       //writeAnswers
     * Description:    //根据题目生成答案，将答案写入Answers.txt中去
     * Calls:          //StackCalculate.getResult();
     * Calls By:       //produceFiles();
     */
    void writeAnswers(String[] exercises) throws Exception
    {
        FileWriter Answers = new FileWriter("Answers.txt");
        StackCalculate a = new StackCalculate();//生成StackCalculate的一个实例对象
        for(int i = 0;i < exercises.length;i++)
        {
            String result = a.getResult(exercises[i]);
            Answers.write((i+1) + ":" + result+"\n");
        }
        Answers.close();
    }
    //随机生成1到3个运算符个数
    int getSignNumber()
    {
        return (int)(Math.random() * 3) + 1;
    }
    //用1，2，3，4来代表"+""-""*""/"
    String getSign()
    {
        while(true)
        {
            int a = (int) (Math.random() * 4) + 1;
            if (a == 1 && hasPlus)
                return "+";
            if (a == 2 && hasMinus)
                return "-";
            if (a == 3 && hasMultiply)
                return "×";
            if (a == 4 && hasDivide)
                return "÷";
        }
    }
    //随机确定是否有括号；
    boolean hasBrackets()
    {
        return !new Random().nextBoolean();
    }
    boolean checkResult(String str)
    {
        String a0 = new StackCalculate().getResult(str);
        if (a0.contains("/"))
        {
            String[] a1 = a0.split("/");
            if (Integer.parseInt(a1[0])<0||Integer.parseInt(a1[0])>100)
                return false;
            if (Integer.parseInt(a1[1])<0||Integer.parseInt(a1[1])>100)
            return false;
        }
        else
        {
            if (Integer.parseInt(a0)<minNumber||Integer.parseInt(a0)>maxNumber*2)
                return false;
        }
        if (str.contains("÷")&&a0.contains("/"))
            return false;
        return true;
    }
}
//随机生成整数或分数
class TypeNumber
{
    boolean numberType;
    int minNumber;
    int maxNumber;
    boolean hasFraction;
    public TypeNumber(int minNumber,int maxNumber,boolean hasFraction)
    {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.hasFraction = hasFraction;
        this.numberType = new Random().nextBoolean();
    }
    public String getTNumber()
    {
        if (!hasFraction)
        {
            return getNumber();
        }
        else
        {
            if (numberType)
                return getNumber();
            else
                return getNumber()+"/"+getNumber();
        }
    }
    //产生一个范围内的整数
    String getNumber()
    {
        //排除零
        int number = (int) (Math.random() * (maxNumber - minNumber + 1)) + minNumber;
        while(number == 0)
        {
            number = (int) (Math.random() * (maxNumber - minNumber + 1)) + minNumber;
        }
        return String.valueOf(number);
    }

}
