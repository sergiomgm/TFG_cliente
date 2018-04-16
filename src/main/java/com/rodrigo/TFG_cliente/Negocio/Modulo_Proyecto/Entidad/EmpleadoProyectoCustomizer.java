package com.rodrigo.TFG_cliente.Negocio.Modulo_Proyecto.Entidad;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.oxm.mappings.XMLCompositeObjectMapping;

public class EmpleadoProyectoCustomizer implements DescriptorCustomizer {
    @Override
    public void customize(ClassDescriptor descriptor) throws Exception {
        //********************************************************
        //*******************   PARA EL ID     *******************
        //********************************************************
        XMLCompositeObjectMapping idMapping =
                (XMLCompositeObjectMapping) descriptor.getMappingForAttributeName("id");
        idMapping.setXPath(".");

        descriptor.addPrimaryKeyFieldName("idEmpleado/text()");
        descriptor.addPrimaryKeyFieldName("idProyecto/text()");



    }
}
