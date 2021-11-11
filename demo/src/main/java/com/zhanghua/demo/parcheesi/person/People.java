package com.zhanghua.demo.parcheesi.person;

import com.zhanghua.demo.parcheesi.nodetype.NodeType;

public class People {
    //名字
    private String name;
    //几次到达终点
    private int count;
    //选择的点数
    private int selectPoint;
    //实际的点数
    private int resultPoint;
    //每次行进到的节点位置
    private int walkNodePos;
    //每次行进到位置节点类型
    private NodeType walkNodeType;
    //当前节点位置
    private int currentNodePos = 1;
    //当前节点类型
    private NodeType currentNodeType;

    public People(NodeType initNodeType,  String name) {
        this.name = name;
        currentNodeType = initNodeType;
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (this.count == 0)  {
            this.count = count;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentNodeType(NodeType currentNodeType) {
        this.currentNodeType = currentNodeType;
    }

    public int getSelectPoint() {
        return selectPoint;
    }

    public void setSelectPoint(int selectPoint) {
        this.selectPoint = selectPoint;
    }

    public int getResultPoint() {
        return resultPoint;
    }

    public void setResultPoint(int resultPoint) {
        this.resultPoint = resultPoint;
    }

    public int getWalkNodePos() {
        return walkNodePos;
    }

    public void setWalkNodePos(int walkNodePos) {
        this.walkNodePos = walkNodePos;
    }

    public NodeType getWalkNodeType() {
        return walkNodeType;
    }

    public void setWalkNodeType(NodeType walkNodeType) {
        this.walkNodeType = walkNodeType;
    }

    public int getCurrentNodePos() {
        return currentNodePos;
    }

    public void setCurrentNodePos(int currentNodePos) {
        this.currentNodePos = currentNodePos;
    }

    public NodeType getCurrentNodeType() {
        return currentNodeType;
    }

    public void setCurrentNodeRule(NodeType currentNodeType) {
        this.currentNodeType = currentNodeType;
    }
}
