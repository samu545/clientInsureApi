drop table if exists client_policy;

create table client_policy
(
  id NUMBER not null,
  clientID NUMBER not null,
  policyID varchar(100) not null,
  insured_amount NUMBER not null,
  monthly_premium NUMBER not null,
  discount NUMBER not null,
  description varchar(255) not null,
  primary key(id)
);

ALTER TABLE client_policy
  ADD CONSTRAINT UK_CLIENT_POLICY UNIQUE (clientID, policyID);