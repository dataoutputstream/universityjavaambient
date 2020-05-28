package poo.rasp;

import java.util.*;

public class ObjectModule implements Iterable<Integer> {
	private List<Integer> image = new ArrayList<Integer>();
	public void addInstruction(int opCode, int mode, int operando) {
		image.add(opCode); image.add(mode); image.add(operando);
	} // addInstruction
	public void addInstruction(int opCode) { image.add(opCode); }
	public void addData(int size) {
		if (size <= 0) throw new IllegalArgumentException();
		for (int i = 0; i < size; i++) image.add(i);
	} // addData
	public int size() { return image.size(); }
	public String toString() {
		int p = 0;
		StringBuilder sb = new StringBuilder(500);
		for (int x: image) {
			sb.append(p++); sb.append(' ');
			sb.append(x); sb.append('\n');
		}
		return sb.toString();
	} // toString
	public Iterator<Integer> iterator() {
		return image.iterator();
	} // iterator
} // ObjectModule
