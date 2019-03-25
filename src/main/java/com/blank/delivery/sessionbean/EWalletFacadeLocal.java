/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blank.delivery.sessionbean;

import com.blank.delivery.models.EWallet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aimen.s.a.sasi
 */
@Local
public interface EWalletFacadeLocal {

  void create(EWallet eWallet);

  void edit(EWallet eWallet);

  void remove(EWallet eWallet);

  EWallet find(Object id);

  List<EWallet> findAll();

  List<EWallet> findRange(int[] range);

  int count();
  
  EWallet findByUserId(int id);
  
}
