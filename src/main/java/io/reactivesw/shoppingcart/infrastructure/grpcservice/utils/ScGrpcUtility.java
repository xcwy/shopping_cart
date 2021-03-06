package io.reactivesw.shoppingcart.infrastructure.grpcservice.utils;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * constants declare for grpc service of shopping cart.
 * @author janeli
 */
public final class ScGrpcUtility {

  /**
   * grpc request parameters are invalid.
   */
  public static final String INVALID_ARGUMENT_MSG = "grpc request parameters are invalid";
  /**
   * quantity is out of range.
   */
  public static final String OUT_OF_RANGE_MSG = "quantity is out of range";
  /**
   * resource is exhausted.
   */
  public static final String RESOURCE_EXHAUSTED_MSG = "inventory resource is exhausted";
  /**
   * add product to shopping cart failed.
   */
  public static final String INTERNAL_MSG = "add product to shopping cart failed";
  /**
   * class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ScGrpcUtility.class);

  /**
   * constructor.
   */
  private ScGrpcUtility() {
  }

  /**
   * complete response.
   * @param responseObserver responseObserver
   * @param reply reply
   */
  public static <T> void completeResponse(StreamObserver<T> responseObserver, final T reply) {
    LOGGER.debug("grpc server: reply for client. reply: {}", reply);
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

}
