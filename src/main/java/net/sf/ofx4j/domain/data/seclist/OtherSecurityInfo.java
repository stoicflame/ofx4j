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

/**
 * Info about any other type of security.
 * @see "Section 13.8.5.5, OFX Spec"
 *
 * @author Jon Perlow
 */
@Aggregate( "OTHERINFO" )
public class OtherSecurityInfo extends BaseSecurityInfo {

  private String typeDesc;
  private String assetClass;
  private String fiAssetClass;

  /**
   * Gets a description of the type of security. This is an optional field according to the OFX
   * spec.
   *
   * @return the description of the security
   */
  @Element( name = "TYPEDESC", order = 20)
  public String getTypeDesc() {
    return typeDesc;
  }

  /**
   * Sets a description of the type of security. This is an optional field according to the OFX
   * spec.
   *
   * @param typeDesc the description of the security
   */
  public void setTypeDesc(String typeDesc) {
    this.typeDesc = typeDesc;
  }

  /**
   * Gets the asset class of the option. This is an optional field according to the OFX spec.
   *
   * @return the asset class of the debt
   */
  @Element( name = "ASSETCLASS", order = 30)
  public String getAssetClass() {
    return assetClass;
  }

  /**
   * Sets the asset class of the debt. This is an optional field according to the OFX spec.
   *
   * @param assetClass the asset class of the debt
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
   * Gets the FI-defined asset class of the debt. This is an optional field according to the OFX
   * spec.
   *
   * @return the FI-defined asset class of the debt
   */
  @Element( name = "FIASSETCLASS", order = 40)
  public String getFiAssetClass() {
    return fiAssetClass;
  }

  /**
   * Sets the FI-defined asset class of the debt. This is an optional field according to the OFX
   * spec.
   *
   * @param fiAssetClass the FI-defined asset class of the debt
   */
  public void setFiAssetClass(String fiAssetClass) {
    this.fiAssetClass = fiAssetClass;
  }
}
