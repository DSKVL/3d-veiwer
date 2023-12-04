package ru.nsu.fit.dskvl.gfx.models;

public record Vec4 (double x , double y,
        double z, double w) {
		public Vec4(double x, double y) { this(x, y, 0, 1); }

    public Vec4 apply(Operator op) {
        return new Vec4(
                op.xx()*x + op.xy()*y + op.xz()*z + op.xw()*w,
                op.yx()*x + op.yy()*y + op.yz()*z + op.yw()*w,
                op.zx()*x + op.zy()*y + op.zz()*z + op.zw()*w,
                op.wx()*x + op.wy()*y + op.wz()*z + op.ww()*w);
    }

    public Vec4 normalize() { 
			return new Vec4(x/length(), y/length(), z/length(), 1); 
		}
		public Vec4 multiply(double t) { return new Vec4(x*t, y*t, z*t, w*t); }
		public Vec4 add(Vec4 o) { return new Vec4(x+o.x, y+o.y, z+o.z, w+o.w); }
    public double length() { return Math.sqrt(x*x + y*y + z*z); }
    public static Vec4 xAxis = new Vec4(1, 0, 0, 1);
    public static Vec4 zAxis = new Vec4(0, 0, 1, 1);
}
