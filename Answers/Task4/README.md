# Example 1

## Acceptance Criteria

### Class

getAdverts(sellerId): List<Advert>
canRepostForFree(sellerId): boolean
repostForFree(advertId): void

## AdvertService

### List<Advert> getAdverts(sellerId)

* Returns a valid `List<Advert>` of all adverts associated with the given `sellerId`
* Returns an empty `List<Advert>` if no adverts are found for the given `sellerId`
* List must be able to return the maximum number of adverts.
* Must return in a timely fashion.

* Throws a Exception if sellerId can not be found -- assumed to be tested elsewhere we just ensure the look up is integrated in a mockists fashion.

### boolean canRepostForFree(sellerId)

* Returns `true` if the given sellerId can be reposted for free
* Returns `false` if the given sellerId cannot be reposted for free
* Must return in a timely fashion.

* Throws a Exception if sellerId can not be found -- assumed to be tested elsewhere we just ensure the look up is integrated in a mockists fashion.

### void repostForFree(advertId)

* Ensure the repostForFree flag has been set on a given advertId, in the back in.
* Ensure nothing has changed in the backend when a invalid advertId (a range of invalids).

