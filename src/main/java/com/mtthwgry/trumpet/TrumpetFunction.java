package com.mtthwgry.trumpet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mtthwgry.trumpet.domain.TrumpetRequest;
import com.mtthwgry.trumpet.service.TrumpTweetService;

import java.io.IOException;
import java.net.URISyntaxException;

public class TrumpetFunction implements RequestHandler<TrumpetRequest, String> {

  @Override
  public String handleRequest(TrumpetRequest input, Context context) {
    try {
      return TrumpTweetService.markov(input.getKeySize(), input.getOutputSize());
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  public static void main(String[] args) {
    TrumpetRequest input = new TrumpetRequest();
    input.setKeySize(2);
    input.setOutputSize(30);

    String output = new TrumpetFunction().handleRequest(input, null);

    System.out.println(output);
  }

}
