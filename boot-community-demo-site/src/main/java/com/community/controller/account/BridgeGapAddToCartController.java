/**
 * 
 */
package com.community.controller.account;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.common.web.controller.BroadleafAbstractController;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.catalog.service.CatalogService;
import org.broadleafcommerce.core.order.domain.NullOrderImpl;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.OrderService;
import org.broadleafcommerce.core.order.service.call.OrderItemRequestDTO;
import org.broadleafcommerce.core.order.service.exception.AddToCartException;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.core.web.order.CartState;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.web.core.CustomerState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bridgegap.course.Program;
import com.bridgegap.profile.BridgeGapCustomer;

/**
 * @author naveen.k.ganachari
 *
 */
@Controller
@RequestMapping("/bridgegap-register")
public class BridgeGapAddToCartController extends BroadleafAbstractController {

	@Resource(name = "blCatalogService")
	protected CatalogService catalogService;

	@Resource(name = "blOrderService")
	protected OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public String registerBG(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes attributes) {
		String lView = null;
		Customer lCustomer = CustomerState.getCustomer();
		if (lCustomer != null) {
			BridgeGapCustomer lBgCustomer = (BridgeGapCustomer) lCustomer;
			if (!lBgCustomer.isLoggedIn()) {
				lView = "/authentication/login";
			} else if ("student".equalsIgnoreCase(lBgCustomer.getAccountType())) {
				if (lBgCustomer.getPrograms() != null && !lBgCustomer.getPrograms().isEmpty()) {
					for (Program lProgram : lBgCustomer.getPrograms()) {
						if (lProgram.getName().equalsIgnoreCase("Bridge gap solutions for students")) {
							lView = "redirect:/program";
							attributes.addAttribute("programId", lProgram.getId());
						}
					}
				} else {
					List<Product> lProducts = catalogService.findProductsByName("Bridge Gap Solution For Students");
					if (lProducts != null) {
						Product lBGProduct = lProducts.get(0);
						Order cart = CartState.getCart();
						if (cart == null || cart instanceof NullOrderImpl) {
							cart = orderService.createNewCartForCustomer(CustomerState.getCustomer(request));
						}
						try {
							if (cart.getItemCount() == 0) {
								OrderItemRequestDTO lOrderItem = new OrderItemRequestDTO(lBGProduct.getId(), 1);
								cart = orderService.addItem(cart.getId(), lOrderItem, false);
								cart = orderService.save(cart, true);
							}
							lView = "redirect:/cart";
						} catch (AddToCartException e) {
							e.printStackTrace();
						} catch (PricingException e) {
							e.printStackTrace();
						}

					}
				}
			}
		}
		return lView;

	}

}
