package ru.nsu.fit.dskvl.gfx.models;

public class Line2D {
    public Vec4 begin, end;

    public Line2D(Vec4 begin, Vec4 end) {
        this.begin = begin;
        this.end = end;
    }

    public Line3D apply(Operator operator) {
        return new Line3D(begin.apply(operator), end.apply(operator));
    }
}
