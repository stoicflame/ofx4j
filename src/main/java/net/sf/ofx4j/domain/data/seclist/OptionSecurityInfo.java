/*
 * Copyright 2010 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.ofx4j.domain.data.seclist;

import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

import java.util.Date;

/**
 * Info about an option security.
 * @see "Section 13.8.5.4, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "OPTINFO" )
public class OptionSecurityInfo extends BaseSecurityInfo {

  private String optionType;
  private Double strikePrice;
  private Date expirationDate;
  private Integer sharesPerContact;
  private SecurityId underlyingSecurity;
  private String assetClass;
  private String fiAssetClass;

  /**
   * Gets the type of option. One of "PUT" or "CALL". This is a required field according to the
   * OFX spec.
   *
   * @return the option type
   */
  @Element( name = "OPTTYPE", order = 20)
  public String getOptionType() {
    return optionType;
  }

  /**
   * Sets the type of option. One of "PUT" or "CALL". This is a required field according to the
   * OFX spec.
   *
   * @param optionType the option type
   */
  public void setOptionType(String optionType) {
    this.optionType = optionType;
  }

  /**
   * Gets the option type as a well-known enum value.
   *
   * @return the option type or null if it's not one of the well-known types
   */
  public OptionType getOptionTypeEnum() {
    return OptionType.fromOfx(getOptionType());
  }

  /**
   * Gets the strike price of the option. This is a required field according to the OFX spec.
   *
   * @return the option strike price
   */
  @Element( name = "STRIKEPRICE", order = 30)
  public Double getStrikePrice() {
    return strikePrice;
  }

  /**
   * Sets the strike price of the option. This is a required field according to the OFX spec.
   *
   * @param strikePrice the option strike price
   */
  public void setStrikePrice(Double strikePrice) {
    this.strikePrice = strikePrice;
  }

  /**
   * Gets the expiration date of the option. This is a required field according to the OFX spec.
   *
   * @return the expiration date of the option
   */
  @Element( name = "DTEXPIRE", order = 40)
  public Date getExpirationDate() {
    return expirationDate;
  }

  /**
   * Sets the expiration date of the option. This is a required field according to the OFX spec.
   *
   * @param expirationDate the expiration date of the option
   */
  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  /**
   * Gets the number of shares per option contact. This is a required field according to the OFX
   * spec.
   *
   * @return the number of shares per option contact
   */
  @Element( name = "SHPERCTRCT", order = 50)
  public Integer getSharesPerContact() {
    return sharesPerContact;
  }

  /**
   * Sets the number of shares per option contact. This is a required field according to the OFX
   * spec.
   *
   * @param sharesPerContact the number of shares per option contact
   */
  public void setSharesPerContact(Integer sharesPerContact) {
    this.sharesPerContact = sharesPerContact;
  }

  /**
   * Gets the security id of the underling security. This is an optional field according to the OFX
   * spec.
   *
   * @return the security id of the underlying security
   */
  @Element( name = "SECID", order = 60)
  public SecurityId getUnderlyingSecurity() {
    return underlyingSecurity;
  }

  /**
   * Sets the security id of the underling security. This is an optional field according to the OFX
   * spec.
   *
   * @param underlyingSecurity the security id of the underlying security
   */
  public void setUnderlyingSecurity(SecurityId underlyingSecurity) {
    this.underlyingSecurity = underlyingSecurity;
  }

  /**
   * Gets the asset class of the option. This is an optional field according to the OFX spec.
   *
   * @return the asset class of the option
   */
  @Element( name = "ASSETCLASS", order = 70)
  public String getAssetClass() {
    return assetClass;
  }

  /**
   * Sets the asset class of the option. This is an optional field according to the OFX spec.
   *
   * @param assetClass the asset class of the option
   */
  public void setAssetClass(String assetClass) {
    this.assetClass = assetClass;
  }

  /**
   * Gets the assert class as one of the well-known types.
   *
   * @return the asset class or null if it's not one of the well-known types
   */
  public AssetClass getAssetClassEnum() {
    return AssetClass.fromOfx(getAssetClass());
  }

  /**
   * Gets the FI-defined asset class of the option. This is an optional field according to the OFX
   * spec.
   *
   * @return the FI-defined asset class of the option
   */
  @Element( name = "FIASSETCLASS", order = 80)
  public String getFiAssetClass() {
    return fiAssetClass;
  }

  /**
   * Sets the FI-defined asset class of the option. This is an optional field according to the OFX
   * spec.
   *
   * @param fiAssetClass the FI-defined asset class of the option
   */
  public void setFiAssetClass(String fiAssetClass) {
    this.fiAssetClass = fiAssetClass;
  }
}
