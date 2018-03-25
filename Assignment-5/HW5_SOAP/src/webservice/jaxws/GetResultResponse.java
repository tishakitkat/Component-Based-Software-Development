
package webservice.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getResultResponse", namespace = "http://webservice/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getResultResponse", namespace = "http://webservice/")
public class GetResultResponse {

    @XmlElement(name = "return", namespace = "")
    private List<controller.Student> _return;

    /**
     * 
     * @return
     *     returns List<Student>
     */
    public List<controller.Student> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<controller.Student> _return) {
        this._return = _return;
    }

}
