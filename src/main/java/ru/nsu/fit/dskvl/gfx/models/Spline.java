package ru.nsu.fit.dskvl.gfx.models;

import java.util.ArrayList;

public class Spline {
    private final ArrayList<Vec4> controlPoints = new ArrayList<>();
    private final ArrayList<Vec4> splinePoints = new ArrayList<>();

    public ArrayList<Line2D> getSplineLines() {
        return splineLines;
    }

    private final ArrayList<Line2D> splineLines = new ArrayList<>();
    private int N = 100;
    private double stepN = 1.0/N;

    public Spline(int numberOfPoints) {
        for (int i = 0; i < numberOfPoints; i++)
            controlPoints.add(new Vec4(
									i * ((double) 1 / numberOfPoints) - 0.5 + 0.5/(numberOfPoints), 
									0.2));
        recalculate();
    }

    public void recalculate() {
        splinePoints.clear();
        splineLines.clear();
        for (int i = 0; i < controlPoints.size() - 3; i++) {
            var polynomial = calculatePolynomial(i);
            var segmentPoints = segmentPoints(polynomial);
            splinePoints.addAll(segmentPoints);
        }
        for (int i = 1; i < splinePoints.size(); i++)
            splineLines.add(new Line2D(
									splinePoints.get(i-1),
									splinePoints.get(i))
						);
    }

    public ArrayList<Vec4> segmentPoints(Vec4[] polynomial) {
        double t = 0;
        var segmentPoints = new ArrayList<Vec4>();
        for (int i = 0 ; i <= N; i++, t+=stepN) {
            var A = polynomial[0];
						var B = polynomial[1];
          	var C = polynomial[2];
            var	D = polynomial[3];
            var point = A.multiply(t*t*t)
                    .add(B.multiply(t*t))
                    .add(C.multiply(t))
                    .add(D);
            segmentPoints.add(point);
        }
        return segmentPoints;
    }

    public Spline() {
        this(0);
    }

    private Vec4[] calculatePolynomial(int index) {
        Vec4 P0 = controlPoints.get(index);
        Vec4 P1 = controlPoints.get(index+1);
        Vec4 P2 = controlPoints.get(index+2);
        Vec4 P3 = controlPoints.get(index+3);

        //A*t^3 + B*t^2 + C*t + D
				//only coefficients
        return new Vec4[]{
        			P0.multiply(-1)
                .add(P1.multiply(3))
                .add(P2.multiply(-3))
                .add(P3)
                .multiply(1.0/6),

        			P0.multiply(3)
                .add(P1.multiply(-6))
                .add(P2.multiply(3))
                .multiply(1.0/6),
        			
							P0.multiply(-3)
                .add(P2.multiply(3))
                .multiply(1.0/6),
							
        			P0.add(P1.multiply(4))
                .add(P2)
                .multiply(1.0/6)
				};
    }

    public ArrayList<Vec4> getControlPoints() { return controlPoints; }
    public ArrayList<Vec4> getSplinePoints() { return splinePoints; }
    public int getN() { return N; }

    public void setN(int n) {
        N = n;
        stepN = 1.0/N;
        recalculate();
    }
}
