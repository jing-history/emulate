package tk.jingzing.demo;

import javax.xml.bind.annotation.*;

/**
 * @Description:what to do
 * Created by Louis Wang on 2016/9/1.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"country"})
@XmlRootElement(name = "GetCountryResponse")
public class GetCountryResponse {

    @XmlElement(name = "Algorithm", required = true)
    protected Country country;

    /**
     * Gets the value of the algorithm property.
     *
     * @return
     *     possible object is
     *     {@link Country }
     *
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the value of the algorithm property.
     *
     * @param value
     *     allowed object is
     *     {@link Country }
     *
     */
    public void setCountry(Country value) {
        this.country = value;
    }
}
