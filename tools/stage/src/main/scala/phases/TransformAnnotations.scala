// See LICENSE

package chipyard.stage.phases

import chisel3.stage.ChiselOutputFileAnnotation
import firrtl.AnnotationSeq
import firrtl.options.Viewer.view
import firrtl.options.{Dependency, Phase}
import chipyard.stage._

/** Transforms RocketChipAnnotations into those used by other stages */
class TransformAnnotations extends Phase with PreservesAll with HasChipyardStageUtils {

  override val prerequisites = Seq(Dependency[Checks])
  override val optionalPrerequisiteOf = Seq(Dependency[chisel3.stage.phases.AddImplicitOutputFile])

  override def transform(annotations: AnnotationSeq): AnnotationSeq = {
    /** Construct output file annotation for emission */
    new ChiselOutputFileAnnotation(view[ChipyardOptions](annotations).longName.get) +: annotations
  }
}
