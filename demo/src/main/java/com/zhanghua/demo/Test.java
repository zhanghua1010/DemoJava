package com.zhanghua.demo;

import com.zhanghua.demo.parcheesi.nodetype.NodeRule;
import com.zhanghua.demo.parcheesi.nodetype.NodeType;
import com.zhanghua.demo.parcheesi.person.People;

import java.util.HashMap;
import java.util.Random;

public class Test {
    private static HashMap<Integer, NodeType> maps;
    private static NodeRule nodeRule;
    private static final int MAX_POINT = 6; //骰子的最大点数
    private static StringBuilder sb = new StringBuilder();
    private static int victoryCount;

    public static void main(String[] args) {

        //创建测试数据
        victoryCount = 0;
        maps = createTestData();
        People a = new People(maps.get(1), "甲");
        People b = new People(maps.get(1), "乙");
        People c = new People(maps.get(1), "丙");
        nodeRule = new NodeRule();
        nodeRule.setPos2TypeMaps(maps);

        //开始
        System.out.println("投掷和行走结果：\n 0："
                +  a.getName() + "在节点" + a.getCurrentNodePos() + ","
                +  b.getName() + "在节点" + b.getCurrentNodePos() + ","
                +  c.getName() + "在节点" + c.getCurrentNodePos() + ",(甲乙丙顺序投掷)");

        int count = 0;
        do{
            count++;
            System.out.println("\n第" + count + "轮");

            walkPath(a, count);
            if (a.getCurrentNodePos() != maps.size() && a.getCurrentNodePos() != 1) {
                if (a.getCurrentNodePos() == b.getCurrentNodePos()) {
                    b.setCurrentNodePos(1);
                    b.setCurrentNodeType(NodeType.A);
                    System.out.println("  将" + b.getName() + "踢回到节点1");
                }

                if (a.getCurrentNodePos() == c.getCurrentNodePos()) {
                    c.setCurrentNodePos(1);
                    c.setCurrentNodeType(NodeType.A);
                    System.out.println("  将" + c.getName() + "踢回到节点1");
                }
            }


            walkPath(b, count);
            if (b.getCurrentNodePos() != maps.size() && b.getCurrentNodePos() != 1) {
                if (a.getCurrentNodePos() == b.getCurrentNodePos()) {
                    a.setCurrentNodePos(1);
                    a.setCurrentNodeType(NodeType.A);
                    System.out.println("  将" + a.getName() + "踢回到节点1");
                }

                if (b.getCurrentNodePos() == c.getCurrentNodePos()) {
                    c.setCurrentNodePos(1);
                    c.setCurrentNodeType(NodeType.A);
                    System.out.println("  将" + c.getName() + "踢回到节点1");
                }
            }


            walkPath(c, count);
            if (c.getCurrentNodePos() != maps.size() && c.getCurrentNodePos() != 1) {
                if (a.getCurrentNodePos() == c.getCurrentNodePos()) {
                    a.setCurrentNodePos(1);
                    a.setCurrentNodeType(NodeType.A);
                    System.out.println("  将" + a.getName() + "踢回到节点1");
                }

                if (b.getCurrentNodePos() == c.getCurrentNodePos()) {
                    b.setCurrentNodePos(1);
                    b.setCurrentNodeType(NodeType.A);
                    System.out.println("  将" + b.getName() + "踢回到节点1");
                }
            }

        } while (a.getCurrentNodePos() < maps.size()
                || b.getCurrentNodePos() < maps.size()
                || c.getCurrentNodePos() < maps.size());

        System.out.println("\n到达终点所用轮次数：\n" + a.getName() + "：" + a.getCount());
        System.out.println(b.getName() + "：" + b.getCount());
        System.out.println(c.getName() + "：" + c.getCount() +
                "\n\n比赛结果：" + sb.toString() + "获胜");
    }

    public static void walkPath(People p, int count) {

        if (p.getCurrentNodePos() == maps.size()) {
            System.out.println(p.getName() + "：已到终点.");

            if (count == victoryCount) {//记录胜利那轮，同时到达终的人
                sb.append( "," + p.getName());
            }

            if (victoryCount == 0) {
                victoryCount = count;
                sb.append(p.getName());
            }

            return;
        }

        Random random = new Random();
        int selectPoint, resultPoint;
        int leftPint = maps.size() - p.getCurrentNodePos();
        if (leftPint >= MAX_POINT) {
            selectPoint = random.nextInt(MAX_POINT) + 1;   //[1, MAX_POINT + 1)
            resultPoint = random.nextInt(selectPoint) + 1; //[1, selectPoint + 1)
        } else {
            selectPoint = random.nextInt(leftPint) + 1;   //[1, leftPint + 1)
            resultPoint = random.nextInt(selectPoint) + 1; //[1, selectPoint + 1)
        }

        if (p.getCurrentNodeType() == NodeType.C) {
            System.out.println(p.getName() + "：投掷选择" + selectPoint + ", 结果" + resultPoint
                    + "，投掷无效一次");
            p.setCurrentNodeType(NodeType.A);
            return;
        }

        p.setSelectPoint(selectPoint);
        p.setResultPoint(resultPoint);
        int walkPos = p.getResultPoint() + p.getCurrentNodePos();
        p.setWalkNodePos(p.getResultPoint() + p.getCurrentNodePos());
        p.setWalkNodeType(maps.get(walkPos));
        int currentPos = nodeRule.getResultPosForType(maps.get(walkPos), walkPos);
        p.setCurrentNodePos(currentPos);
        p.setCurrentNodeType(maps.get(currentPos));

        if (currentPos == 1) {
            System.out.println(p.getName() + "：投掷选择" + selectPoint + ", 结果" + resultPoint
                    + ", 前进至节点" + walkPos + ", 回退到出发节点1" );
        } else if (currentPos == maps.size()) {
            p.setCount(count);
            System.out.println(p.getName() + "：投掷选择" + selectPoint + ", 结果" + resultPoint
                    + ", 前进至节点" + walkPos + ", 到达终点" );
        } else {
            if (walkPos > currentPos) {
                System.out.println(p.getName() + "：投掷选择" + selectPoint + ", 结果" + resultPoint
                        + ", 前进至节点" + walkPos + ", 回退到节点" + currentPos );
            } else {
                System.out.println(p.getName() + "：投掷选择" + selectPoint + ", 结果" + resultPoint
                        + ", 前进至节点" + currentPos);
            }
        }
    }

    public static HashMap<Integer, NodeType> createTestData() {
        HashMap<Integer, NodeType> maps = new HashMap<>();
        maps.put(1, NodeType.A);
        maps.put(2, NodeType.A);
        maps.put(3, NodeType.B);
        maps.put(4, NodeType.A);
        maps.put(5, NodeType.C);
        maps.put(6, NodeType.A);
        maps.put(7, NodeType.A);
        maps.put(8, NodeType.D);
        maps.put(9, NodeType.A);
        maps.put(10, NodeType.A);
        maps.put(11, NodeType.E);
        maps.put(12, NodeType.A);
        maps.put(13, NodeType.A);
        maps.put(14, NodeType.A);
        maps.put(15, NodeType.A);
        maps.put(16, NodeType.E);
        maps.put(17, NodeType.B);
        maps.put(18, NodeType.A);
        maps.put(19, NodeType.C);
        maps.put(20, NodeType.A);
        return maps;
    }
}
