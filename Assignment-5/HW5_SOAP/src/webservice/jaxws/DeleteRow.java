
package webservice.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deleteRow", namespace = "http://webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteRow", namespace = "http://webservice/")
public class DeleteRow {

    @XmlElement(name = "arg0", namespace = "")
    private controller.Student arg0;

    /**
     * 
     * @return
     *     returns Student
     */
    public controller.Student getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(controller.Student arg0) {
        this.arg0 = arg0;
    }

}
