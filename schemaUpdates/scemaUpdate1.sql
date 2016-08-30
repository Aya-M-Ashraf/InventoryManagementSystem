USE `inventory_system`;
DELIMITER $$

CREATE TRIGGER `product_BIND` BEFORE INSERT ON product FOR EACH ROW
-- Edit trigger body code below this line. Do not edit lines above this one
begin if(new.quantity <= new.threshold) then set new.threshold_alarm = 1; end if;
if(new.quantity > new.threshold) then set new.threshold_alarm = 0; end if;
if(datediff(new.expiry_date , CURDATE()	) >=60) then set new.expiry_alarm = 0; end if;
if(datediff(new.expiry_date , CURDATE()	) <60) then set new.expiry_alarm = 1; end if; end$$


USE `inventory_system`;
DELIMITER $$

CREATE TRIGGER `product_BUPD` BEFORE UPDATE ON product FOR EACH ROW
-- Edit trigger body code below this line. Do not edit lines above this one
begin if(new.quantity <= new.threshold) then set new.threshold_alarm = 1; end if;
if(new.quantity > new.threshold) then set new.threshold_alarm = 0; end if;
if(datediff(new.expiry_date , CURDATE()	) >=60) then set new.expiry_alarm = 0; end if;
if(datediff(new.expiry_date , CURDATE()	) <60) then set new.expiry_alarm = 1; end if; end$$
