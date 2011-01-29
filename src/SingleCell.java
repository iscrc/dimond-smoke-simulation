import javax.media.j3d.*;
import javax.vecmath.*;

public class SingleCell extends Shape3D
{
  public SingleCell(float halfLength, Appearance app)
  {
    this.setGeometry(createGeometry(halfLength));
    this.setAppearance(app);
  }

  private Geometry createGeometry(float halfLength)
  {
    IndexedQuadArray polyFace = new IndexedQuadArray(8, GeometryArray.COORDINATES, 24);

    polyFace.setCoordinate(0, new Point3f(-halfLength, halfLength, halfLength));
    polyFace.setCoordinate(1, new Point3f(halfLength, halfLength, halfLength));
    polyFace.setCoordinate(2, new Point3f(halfLength, -halfLength, halfLength));
    polyFace.setCoordinate(3, new Point3f(-halfLength, -halfLength, halfLength));
    polyFace.setCoordinate(4, new Point3f(-halfLength, halfLength, -halfLength));
    polyFace.setCoordinate(5, new Point3f(halfLength, halfLength, -halfLength));
    polyFace.setCoordinate(6, new Point3f(halfLength, -halfLength, -halfLength));
    polyFace.setCoordinate(7, new Point3f(-halfLength, -halfLength, -halfLength));

    polyFace.setCoordinateIndex(0, 0);
    polyFace.setCoordinateIndex(1, 1);
    polyFace.setCoordinateIndex(2, 2);
    polyFace.setCoordinateIndex(3, 3);
    polyFace.setCoordinateIndex(4, 0);
    polyFace.setCoordinateIndex(5, 4);
    polyFace.setCoordinateIndex(6, 5);
    polyFace.setCoordinateIndex(7, 1);
    polyFace.setCoordinateIndex(8, 4);
    polyFace.setCoordinateIndex(9, 5);
    polyFace.setCoordinateIndex(10, 6);
    polyFace.setCoordinateIndex(11, 7);
    polyFace.setCoordinateIndex(12, 7);
    polyFace.setCoordinateIndex(13, 6);
    polyFace.setCoordinateIndex(14, 2);
    polyFace.setCoordinateIndex(15, 3);
    polyFace.setCoordinateIndex(16, 0);
    polyFace.setCoordinateIndex(17, 4);
    polyFace.setCoordinateIndex(18, 7);
    polyFace.setCoordinateIndex(19, 3);
    polyFace.setCoordinateIndex(20, 1);
    polyFace.setCoordinateIndex(21, 5);
    polyFace.setCoordinateIndex(22, 6);
    polyFace.setCoordinateIndex(23, 2);

    return polyFace;
  }
}
