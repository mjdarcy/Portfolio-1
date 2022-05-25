import java.util.List;

import javax.swing.JFrame;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class JGraphX2 extends JFrame
{

    private static final long serialVersionUID = -2707712944901661771L;
    SimpleTreeNode<String> root = createSimpleTreeNode();

    public void fillGraphFromModel( mxGraph graph)  {
        graphUpdate();

        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
        	Object vRoot = graph.insertVertex(parent, null, root.getData(), 80, 0, 80, 30);
        	for(SimpleTreeNode n : root.getChildren()) {
        		Object child = graph.insertVertex(parent, null, n.getData(), 0, 0, 80, 30);
        		graph.insertEdge(parent, null, "", vRoot, child);
        		
        		List<SimpleTreeNode> test = n.getChildren();
        		for(SimpleTreeNode n2 : test) {
        			Object grandchild = graph.insertVertex(null, null, n2.getData(), 0, 0, 80, 30);
            		graph.insertEdge(child, null, "", child, grandchild);
            		
            		test = n2.getChildren();
            		for(SimpleTreeNode n3 : test) {
            			Object greatgrandchild = graph.insertVertex(null, null, n3.getData(), 0, 0, 80, 30);
                		graph.insertEdge(grandchild, null, "", grandchild, greatgrandchild);
            		}
        		}
        	}

            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setUseBoundingBox(false);

            layout.execute(parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
    }

    public JGraphX2()
    {
        super("Simple Tree Node");
        mxGraph graph = new mxGraph();
        fillGraphFromModel(graph);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);


    }

    public SimpleTreeNode<String> createSimpleTreeNode() {
        root = new SimpleTreeNode<>("Root (Heroes of Might and Magic III)");

        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("Castle Town");
        child1.addChild("Hero Edric");
        child1.addChild("Hero Adela");

        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Rampart Town");
        child2.addChild("Hero Clancy");
        child2.addChild("Hero Jenova");
        
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("Tower Town");
        child3.addChild("Hero Fafner");
        child3.addChild("Hero Iona");
        
        SimpleTreeNode<String> child4 = new SimpleTreeNode<>("Conflux Town");
        child4.addChild("Hero Erdamon");
        child4.addChild("Hero Ignissa");
        
        SimpleTreeNode<String> child5 = new SimpleTreeNode<>("Necropolis Town");
        child5.addChild("Hero Galthran");
        child5.addChild("Hero Charna");
        
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        root.addChild(child4);
        root.addChild(child5);
        
        child1.getChildren().get(0).addChild("Pikeman Unit");
        child1.getChildren().get(0).addChild("Marksman Unit");
        child1.getChildren().get(1).addChild("Cavalier Unit");
        
        child2.getChildren().get(0).addChild("Centaur Unit");
        child2.getChildren().get(0).addChild("Wood Elf Unit");
        child2.getChildren().get(1).addChild("Green Dragon Unit");
        
        child3.getChildren().get(0).addChild("Gremlin Unit");
        child3.getChildren().get(1).addChild("Stone Gargoyle Unit");
        child3.getChildren().get(0).addChild("Naga Queen Unit");
        
        child4.getChildren().get(0).addChild("Pixie Unit");
        child4.getChildren().get(1).addChild("Fire Elemental Unit");
        child4.getChildren().get(1).addChild("Earth Elemental Unit");
        child4.getChildren().get(1).addChild("Phoenix Unit");
        
        child5.getChildren().get(0).addChild("Skeleton Unit");
        child5.getChildren().get(1).addChild("Zombie Unit");

        
        for (SimpleTreeNode child : root.getChildren()) {
            root.printChildren(child);
        }
        return root;
    }


    // Backup not used
    public void graphUpdate() {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
            //Notice that the parent is the default parent...
            //The hierarchical structure looks great but I cannot collapse/expand the tree.
            Object vDogsRoot = graph.insertVertex(parent, null, "Whale", 80, 0, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "Shar Pei", 0, 0, 80, 30);
            Object v3 = graph.insertVertex(parent, null, "Pug", 0, 0, 80, 30);
            Object v4 = graph.insertVertex(parent, null, "Cocker Spaniel", 0, 0, 80, 30);
            Object v5 = graph.insertVertex(parent, null, "Pit Bull", 0, 0, 80, 30);
            Object v6 = graph.insertVertex(parent, null, "Chihuahua", 0, 0, 80, 30);
            Object v7 = graph.insertVertex(null, null, "GrandPuppy", 0,0,90,30);
            Object v8 = graph.insertVertex(null, null, "GrandPuppy2", 0,0,90,30);
            Object v9 = graph.insertVertex(null, null, "GrandPuppy3", 0,0,90,30);
            graph.insertEdge(parent, null, "", vDogsRoot, v2);
            graph.insertEdge(parent, null, "", vDogsRoot, v3);
            graph.insertEdge(parent, null, "", vDogsRoot, v4);
            graph.insertEdge(parent, null, "", vDogsRoot, v5);
            graph.insertEdge(parent, null, "", vDogsRoot, v6);
            graph.insertEdge(v6, null, "", v6, v7);
            graph.insertEdge(v6, null, "", v6, v8);
            graph.insertEdge(v6, null, "", v6, v9);

            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setUseBoundingBox(false);

            layout.execute(parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public static void main(String[] args)
    {
        JGraphX2 frame = new JGraphX2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 420);
        frame.setVisible(true);
    }

}