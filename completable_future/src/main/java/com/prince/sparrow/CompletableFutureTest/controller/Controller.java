/*
 *     _    __
 *    | |  / /____   _  __
 *    | | / // __ \ | |/_/
 *    | |/ // /_/ /_>  <
 *    |___/ \____//_/|_|
 *      ______             __                   __               _
 *     /_  __/___   _____ / /_   ____   ____   / /____   ____ _ (_)___   _____
 *      / /  / _ \ / ___// __ \ / __ \ / __ \ / // __ \ / __ `// // _ \ / ___/
 *     / /  /  __// /__ / / / // / / // /_/ // // /_/ // /_/ // //  __/(__  )
 *    /_/   \___/ \___//_/ /_//_/ /_/ \____//_/ \____/ \__, //_/ \___//____/
 *                                                    /____/
 *

 * This file is part of the VOX Technologies code base.

 * (c) VOX Technologies <dev@voxtechnologies.net>

 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.

 * GENERAL. The software, documentation and any fonts accompanying this License
 * whether on disk, in read only memory, on any other media or in any other form
 * (collectively the “Software”) are licensed, not sold, to you by VOX Global Technologies Ltd.
 * (“VOX”) for use only under the terms of this License, and VOX reserves all rights
 * not expressly granted to you. The rights granted herein are limited to VOX
 * intellectual property rights in the VOX Software and do not include any
 * other patents or intellectual property rights. You own the media on which
 * the VOX Software is recorded but VOX and/or VOX licensor(s) retain ownership of
 * the Software itself.

 * Author::    Ashkan Zafari  (ashkan.zafari@voxcarrier.com)
 * Copyright:: Copyright (c) 2021-present, VOX Global Technologies Ltd.
 *
 */

// ------------------------------------------------------------------------------------------------
// ================================================================================================
// PACKAGE DEFINITION
// ================================================================================================
// ------------------------------------------------------------------------------------------------
package com.prince.sparrow.CompletableFutureTest.controller;
// ------------------------------------------------------------------------------------------------
// ================================================================================================
// IMPORT DEFINITION
// ================================================================================================
// ------------------------------------------------------------------------------------------------

// ------------------------------------------------------------------------------------------------
// ================================================================================================
// CLASS DEFINITION
// ================================================================================================
// ------------------------------------------------------------------------------------------------

import com.prince.sparrow.CompletableFutureTest.service.AsyncServcie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Controller.
 *
 * <p></p>
 */
@RestController
public class Controller {
  /* ********************************************************************************************
   * CLASS VARIABLES
   *********************************************************************************************/
  private final AsyncServcie asyncServcie;


  /* ********************************************************************************************
   * CONSTRUCTOR METHODS
   *********************************************************************************************/

  /* ********************************************************************************************
   * PRIVATE METHODS
   *********************************************************************************************/
  private String processAsync(){
    return "hello";
  }
  /* ********************************************************************************************
   * PROTECTED METHODS
   *********************************************************************************************/

  /* ********************************************************************************************
   * PUBLIC METHODS
   *********************************************************************************************/
  @GetMapping("/async/test/{id}")
  public CompletableFuture<String> getValueAsync(@PathVariable String id) {
    CompletableFuture<String> completableFuture = asyncServcie.processRequest(id);
    return completableFuture;
  }

  public Controller(AsyncServcie asyncServcie) {
    this.asyncServcie = asyncServcie;
  }
}