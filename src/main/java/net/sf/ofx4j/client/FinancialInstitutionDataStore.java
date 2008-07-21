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

package net.sf.ofx4j.client;

import java.util.List;

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitutionDataStore {

  /**
   * Get the data for the financial institution of the specified id.
   *
   * @param fid The id of the financial institution.
   * @return The financial institution data, or null if none exists for the specified id.
   */
  FinancialInstitutionData getInstitutionData(String fid);

  /**
   * Get the whole list of financial institution data.
   *
   * @return The whole list of financial institution data.
   */
  List<FinancialInstitutionData> getInstitutionDataList();
}
