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

/**
 * @author Ryan Heaton
 */
public interface FinancialInstitutionService {

  /**
   * Get the financial institution by the specified id.
   *
   * @param fid The financial institution id.
   * @return The financial institution, or null if not found.
   */
  public FinancialInstitution getFinancialInstitution(String fid);

  /**
   * Get the financial institution by the specified data.
   *
   * @param data The financial institution data.
   * @return The financial institution, or null if not found.
   */
  public FinancialInstitution getFinancialInstitution(FinancialInstitutionData data);

}
