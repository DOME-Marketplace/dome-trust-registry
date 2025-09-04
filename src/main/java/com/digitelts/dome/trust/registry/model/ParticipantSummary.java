package com.digitelts.dome.trust.registry.model;

import java.util.Objects;
import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-13T11:13:01.155472Z[UTC]", comments = "Generator version: 7.7.0")
public class ParticipantSummary extends TrustedRegistrySummary{

  public ParticipantSummary(String did, String href) {
    super(did, href);
  }

  @Override
  public int hashCode() {
    return Objects.hash(did);
  }
}

