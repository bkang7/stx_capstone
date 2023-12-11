/**
 * Script to export binary masks corresponding to all annotations of an image,
 * optionally along with extracted image regions.
 *
 * Note: Pay attention to the 'downsample' value to control the export resolution!
 *
 * @author Pete Bankhead
 */
import qupath.lib.images.servers.ImageServer
import qupath.lib.objects.PathObject
import qupath.lib.gui.scripting.QPEx
import javax.imageio.ImageIO
import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.Graphics2D 

def draw_shape(Graphics2D canvas, PathObject pathObject) {
    def roi = pathObject.getROI()
    def shape = RoiTools.getShape(roi)
    canvas.translate(0, 0)
    canvas.fill(shape)
 }

// Get the main QuPath data structures
def imageData = getCurrentImageData()
def hierarchy = imageData.getHierarchy()
def server = imageData.getServer()

// Define downsample value for export resolution & output directory, creating directory if necessary
def pathOutput = buildFilePath(QPEx.PROJECT_BASE_DIR, 'masks')
mkdirs(pathOutput)


annotations = hierarchy.getAnnotationObjects()

// Request all objects from the hierarchy & filter only the annotations
def save_tiff_annotation (anno_all, class_name,  s, out) {
    def imgMask = new BufferedImage(s.getWidth(), s.getHeight(), BufferedImage.TYPE_BYTE_GRAY)
    def g2d = imgMask.createGraphics()
    g2d.setColor(Color.WHITE)
    g2d.scale(1.0, 1.0)
    def anno=anno_all.findAll{it.getPathClass() == getPathClass(class_name)}
    anno.each {draw_shape(g2d, it)}
    g2d.dispose()
    String name = String.format('%s_', s.getMetadata().getName().split("\\."))
    def fileMask = new File(out, name+'_'+class_name+'_' +'mask.tif')
    ImageIO.write(imgMask, "TIFF", fileMask)
}
save_tiff_annotation(annotations, 'Visium', server, pathOutput)
save_tiff_annotation(annotations, 'training_validation', server, pathOutput)
save_tiff_annotation(annotations, 'artefact', server, pathOutput)
save_tiff_annotation(annotations, 'Tissue', server, pathOutput)


//def annotations = hierarchy.getAnnotationObjects().findAll{it.getPathClass() == getPathClass("Visium")}[0]
//annotations.each {draw_shape(g2d, it)}
//g2d.dispose()
//String name = String.format('%s_', server.getMetadata().getName().split("\\."))
//def fileMask = new File(pathOutput, name +'mask.tif')
//ImageIO.write(imgMask, "TIFF", fileMask)
//



print "Done!"



