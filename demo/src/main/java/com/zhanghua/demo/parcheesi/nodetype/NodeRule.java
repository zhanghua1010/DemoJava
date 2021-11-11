package com.zhanghua.demo.parcheesi.nodetype;

import java.util.HashMap;

public class NodeRule {

    private int resultPos;
    private HashMap<Integer, NodeType> pos2TypeMaps= new HashMap<Integer, NodeType>();

    public void setPos2TypeMaps(HashMap<Integer, NodeType> maps) {
        pos2TypeMaps = maps;
    }


    public int  getResultPosForType(NodeType nodeType,  int pos) {
        NodeType intoType = nodeType;//E类型特殊处理
        resultPos = pos;
        do {
            if (nodeType == NodeType.B) {
                resultPos--;
            } else if (nodeType == NodeType.D) {
                resultPos = 1;
            } else if (nodeType == NodeType.E) {
                resultPos = searchLastTypeE(resultPos);
                intoType = NodeType.E;
            } else {}

            nodeType = pos2TypeMaps.get(resultPos);

        } while (nodeType == NodeType.B || nodeType == NodeType.D
                || (nodeType == NodeType.E && intoType != NodeType.E ));

        return resultPos;
    }

    public int  searchLastTypeE(int currentEPos) {

        int lastEPos = 0;

        for (int i = currentEPos - 1; i > 0; i--) {
            if (pos2TypeMaps.get(i) == NodeType.E) {
                lastEPos = i;
                break;
            }
        }

        if (lastEPos == 0) {
            lastEPos = currentEPos;
        }

        return lastEPos;

    }

}
