/**
 * 
 */
package com.heavenHR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavenHR.model.Offer;

/**
 * @author Gustavo
 *
 */
public interface OfferRepository extends JpaRepository<Offer, Long> {

}
