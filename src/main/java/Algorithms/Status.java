package Algorithms;

import TCN.*;

public class Status {



    private boolean dc;
    private  boolean hasDerived;
    private int retValue;
    private RULE rule;
    private STNUPath path;
    private Edge derivedEdge;
    private int step;

    public Status(boolean dc, boolean hasDerived, int retValue, RULE rule, STNUPath path, Edge derivedEdge) {
        this.dc = dc;
        this.hasDerived = hasDerived;
        this.retValue = retValue;
        this.rule = rule;
        this.path = path;
        this.derivedEdge = derivedEdge;
    }

    public Status (){
        this.retValue = Integer.MAX_VALUE;
        this.hasDerived = false;
        this.dc = true;
    }

    public Status (boolean hasDerived, boolean dc){
        this.dc = dc;
        this.hasDerived = hasDerived;
    }

    public void setHasDerived(boolean hasDerived) {
        this.hasDerived = hasDerived;
    }

    public boolean isDc() {
        return dc;
    }

    public void setDc(boolean dc) {
        this.dc = dc;
    }

    public boolean hasDerived() {
        return hasDerived;
    }

    public void setDerived(boolean hasDerived) {
        this.hasDerived = hasDerived;
    }

    public int getRetValue() {
        return retValue;
    }

    public void setRetValue(int retValue) {
        this.retValue = retValue;
    }

    public RULE getRule() {
        return rule;
    }

    public void setRule(RULE rule) {
        this.rule = rule;
    }

    public STNUPath getPath() {
        return path;
    }

    public void setPath(STNUPath path) {
        this.path = path;
    }

    public Edge getDerivedEdge() {
        return derivedEdge;
    }

    public void setDerivedEdge(Edge derivedEdge) {
        this.derivedEdge = derivedEdge;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Status{" +
                "dc=" + dc +
                ", hasDerived=" + hasDerived +
                ", retValue=" + retValue +
                ", rule=" + rule +
                ", path=" + path +
                ", derivedEdge=" + derivedEdge +
                ", step=" + step +
                '}';
    }

}
