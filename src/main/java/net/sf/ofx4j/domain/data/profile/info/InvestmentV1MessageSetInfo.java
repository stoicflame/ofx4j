/*
 * Copyright 2008 Web Cohesion
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

package net.sf.ofx4j.domain.data.profile.info;

import net.sf.ofx4j.domain.data.profile.VersionSpecificMessageSetInfo;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.meta.Aggregate;
import net.sf.ofx4j.meta.Element;

/**
 * @see "Section 13.7.1.1, OFX Spec"
 *
 * @author Jon Perlow
 * @author Ryan Heaton
 */
@Aggregate ( "INVSTMTMSGSETV1" )
public class InvestmentV1MessageSetInfo extends VersionSpecificMessageSetInfo {

  private Boolean supportsStatementsDownload;
  private Boolean supportsOpenOrdersDownload;
  private Boolean supportsPositionsDownload;
  private Boolean supportsBalanceDownload;
  private Boolean supportsEmail;
  private Boolean supports401kInformation;
  private Boolean supportsClosingStatements;

  public MessageSetType getMessageSetType() {
    return MessageSetType.investment;
  }

  @Element( name = "TRANDNLD", required=true, order = 10)
  public Boolean getSupportsStatementsDownload() {
    return supportsStatementsDownload;
  }

  public void setSupportsStatementsDownload(Boolean supportsStatementsDownload) {
    this.supportsStatementsDownload = supportsStatementsDownload;
  }

  @Element( name = "OODNLD", required=true, order = 20)
  public Boolean getSupportsOpenOrdersDownload() {
    return supportsOpenOrdersDownload;
  }

  public void setSupportsOpenOrdersDownload(Boolean supportsOpenOrdersDownload) {
    this.supportsOpenOrdersDownload = supportsOpenOrdersDownload;
  }

  @Element( name = "POSDNLD", required=true, order = 30)
  public Boolean getSupportsPositionsDownload() {
    return supportsPositionsDownload;
  }

  public void setSupportsPositionsDownload(Boolean supportsPositionsDownload) {
    this.supportsPositionsDownload = supportsPositionsDownload;
  }

  @Element( name = "BALDNLD", required=true, order = 40)
  public Boolean getSupportsBalanceDownload() {
    return supportsBalanceDownload;
  }

  public void setSupportsBalanceDownload(Boolean supportsBalanceDownload) {
    this.supportsBalanceDownload = supportsBalanceDownload;
  }

  @Element( name = "CANEMAIL", required=true, order = 50)
  public Boolean getSupportsEmail() {
    return supportsEmail;
  }

  public void setSupportsEmail(Boolean supportsEmail) {
    this.supportsEmail = supportsEmail;
  }

  @Element( name = "INV401KDNLD", order = 60)
  public Boolean getSupports401kInformation() {
    return supports401kInformation;
  }

  public void setSupports401kInformation(Boolean supports401kInformation) {
    this.supports401kInformation = supports401kInformation;
  }

  @Element( name = "CLOSINGAVAIL", order = 70)
  public Boolean getSupportsClosingStatements() {
    return supportsClosingStatements;
  }

  public void setSupportsClosingStatements(Boolean supportsClosingStatements) {
    this.supportsClosingStatements = supportsClosingStatements;
  }
}