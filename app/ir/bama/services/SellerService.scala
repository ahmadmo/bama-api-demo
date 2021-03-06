/*
 * Copyright 2017 Ahmad Mozafarnia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.bama.services

import javax.inject.{Inject, Singleton}

import ir.bama.models.Seller
import ir.bama.models.SellerType.SellerType
import ir.bama.repositories.SellerRepo

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author ahmad
  */
@Singleton
class SellerService @Inject()(sellerRepo: SellerRepo)(implicit ec: ExecutionContext) extends BaseService[Seller[_], SellerRepo](sellerRepo) {

  import repo.dbConfig._
  import profile.api._

  def load(id: Long, filterPublic: Boolean): Future[Option[Seller[_]]] =
    db.run(repo.load(id, filterPublic))

  def findIdAndTypeByUserId(userId: Long): Future[Option[(Long, SellerType)]] =
    db.run(repo.findIdAndTypeByUserId(userId))

  def loadByUserId(userId: Long): Future[Option[Seller[_]]] =
    db.run(repo.loadByUserId(userId))

  def updatePhoto(userId: Long, name: String): Future[Option[Long]] =
    db.run(repo.updatePhoto(userId, name))

  def deletePhoto(userId: Long): Future[Option[(Long, Option[String])]] =
    db.run(repo.deletePhoto(userId))

  def listByType(`type`: SellerType, range: Option[Range]): Future[Seq[Seller[_]]] =
    db.run(repo.listByType(`type`, range))

  def listByProvinceId(provinceId: Long, range: Option[Range]): Future[Seq[Seller[_]]] =
    db.run(repo.listByProvinceId(provinceId, range))

  def listByCityId(cityId: Long, range: Option[Range]): Future[Seq[Seller[_]]] =
    db.run(repo.listByCityId(cityId, range))

  def listByTypeAndProvinceId(`type`: SellerType, provinceId: Long, range: Option[Range]): Future[Seq[Seller[_]]] =
    db.run(repo.listByTypeAndProvinceId(`type`, provinceId, range))

  def listByTypeAndCityId(`type`: SellerType, cityId: Long, range: Option[Range]): Future[Seq[Seller[_]]] =
    db.run(repo.listByTypeAndCityId(`type`, cityId, range))

}
