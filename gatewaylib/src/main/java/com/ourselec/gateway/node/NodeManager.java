package com.ourselec.gateway.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 节点管理类
 *
 * @author yangtianfei(ytf2737179@163.com)
 */
public class NodeManager {

	private List<Node> nodes;//

	private static NodeManager nodeManager;

	public static NodeManager getInstance() {
		if (nodeManager == null)
			nodeManager = new NodeManager();
		return nodeManager;
	}

	private NodeManager() {
		nodes = new ArrayList<Node>();
	}

	public synchronized void addNode(Node node) {
		Iterator<Node> iterator = nodes.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getNoteAddress() == node.getNoteAddress()) {
				iterator.remove();
			}
		}
		nodes.add(node);
	}

	public synchronized Node getNode(int noteAddress) {
		Iterator<Node> iterator = nodes.iterator();

		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (node.getNoteAddress() == noteAddress)
				return node;
		}
		return null;
	}

	public synchronized int getNodeCount() {
		return nodes.size();
	}

	public synchronized void deleteNode(int nodeAddress) {
		Iterator<Node> iterator = nodes.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getNoteAddress() == nodeAddress) {
				iterator.remove();
			}
		}
	}

	public synchronized boolean deleteAllNode() {
		nodes.clear();
		return true;
	}

	public boolean isNodeExist(int nodeAddress) {
		Iterator<Node> iterator = nodes.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getNoteAddress() == nodeAddress) {
				return true;
			}
		}
		return false;
	}

	public synchronized int checkNodesState() {
		Iterator<Node> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			long cTime = System.currentTimeMillis();
			Node node = iterator.next();
			if (cTime - node.getLifeTime() > 15 * 1000) {
				iterator.remove();
				return node.getNoteAddress();
			}
		}
		return -1;
	}

}