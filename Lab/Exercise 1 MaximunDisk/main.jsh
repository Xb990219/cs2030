double epsilon = 1E-15;

double distanceBetween(Point p, Point q) {
	double dx = p.getx() - q.getx();
	double dy = p.gety() - q.gety();

	return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
}

boolean circleContainsPoint(Circle c, Point p) {
	return distanceBetween(c.getCentre(), p) < c.getRadius() + epsilon;
}

Circle createUnitCircle(Point p, Point q){
	Point midPoint = p.midPoint(q);
	double distanceMoved = Math.sqrt( 1 - (distanceBetween(p,q)/2) * (distanceBetween(p,q)/2) );
	Point centre = midPoint.moveTo ( midPoint.angleTo(q) + Math.PI / 2, distanceMoved );
	return new Circle ( centre, 1 );

}

int findMaxDiscCoverage(ImList<Point> points) {
	int maxDiscCoverage = 0;
	int numOfPoints = points.size();

	for (int i = 0; i < numOfPoints - 1; i++) {
		for (int j = i + 1; j < numOfPoints; j++) {
			Point p = points.get(i);
			Point q = points.get(j);
			double distPQ = distanceBetween(p, q);
			if (distPQ < 2.0 + epsilon && distPQ > 0) {
                                Circle c = createUnitCircle(p,q);
				int coverage = 0;
				for (Point point : points) {
					if (c.contains(point)) {
						coverage = coverage + 1;
					}
				}
				if (coverage > maxDiscCoverage) {
					maxDiscCoverage = coverage;
				}
			}
		}
	}
	return maxDiscCoverage;
}
