package tk.jingzing.demo;

import javax.xml.bind.annotation.*;

/**
 * @Description:what to do
 * Created by Louis Wang on 2016/9/1.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"name"})
@XmlRootElement(name = "GetCountryRequest")
public class GetCountryRequest {


    @XmlElement(required = true)
    protected String name;

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }
}
