ALTER TABLE `inventory_system`.`the_order` DROP FOREIGN KEY `fk_order_product1` ;
ALTER TABLE `inventory_system`.`the_order` 
  ADD CONSTRAINT `fk_order_product1`
  FOREIGN KEY (`product_id` )
  REFERENCES `inventory_system`.`product` (`id` )
  ON DELETE CASCADE
  ON UPDATE CASCADE;