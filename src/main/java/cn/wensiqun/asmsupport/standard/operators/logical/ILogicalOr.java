/**    
 *  Asmsupport is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * 
 */
package cn.wensiqun.asmsupport.standard.operators.logical;

import cn.wensiqun.asmsupport.standard.Parameterized;

/**
 * 
 * @author 温斯群(Joe Wen)
 *
 */
public interface ILogicalOr<_P extends Parameterized> extends Parameterized {

	/**
	 * Execute & with other parameter.
	 * 
	 * @param para
	 * @return
	 */
	ILogicalAnd<_P> logicalAnd(_P para);

	/**
	 * Execute | with other parameter.
	 * 
	 * @param para
	 * @return
	 */
	ILogicalOr<_P> logicalOr(_P para);

	/**
	 * Execute ^ with other parameter.
	 * 
	 * @param para
	 * @return
	 */
	ILogicalXor<_P> logicalXor(_P para);

	/**
	 * Execute && with other parameter.
	 * 
	 * @param para
	 * @return
	 */
	IShortCircuitAnd<_P> and(_P para);

	/**
	 * Execute || with other parameter.
	 * 
	 * @param para
	 * @return
	 */
	IShortCircuitOr<_P> or(_P para);
}
